package proyectoAPPBackend.proyectoAPPBackend.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.exceptions.CustomException;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.JwtDto;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.ListarUsuarioPorRol;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.LoginUsuario;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.NuevoUsuario;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Rol;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.enums.RolNombre;
import proyectoAPPBackend.proyectoAPPBackend.security.jwt.JwtProvider;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.UsuarioRepository;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail) {
        return usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail);
    }

    public Optional<Usuario> getByTokenPassword(String tokenPassword) {
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public boolean validarEstadoUsuario(String nombreUsuario, boolean estado) {
        return usuarioRepository.existsByNombreUsuarioAndEstado(nombreUsuario, estado);
    }

    public boolean validarEstadoUsuarioEmail(String email, boolean estado) {
        return usuarioRepository.existsByEmailAndEstado(email, estado);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public JwtDto login(LoginUsuario loginUsuario) {
       
        boolean estado = validarEstadoUsuario(loginUsuario.getNombreUsuario(), true) || validarEstadoUsuarioEmail(loginUsuario.getNombreUsuario(), true);

        if (!estado) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Usuario inactivo");

        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
                            loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            return new JwtDto(jwt);
          
        }

    }

    public JwtDto refresh(JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }

    // public Mensaje save(NuevoUsuario nuevoUsuario){
    // if(usuarioRepository.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
    // throw new CustomException(HttpStatus.BAD_REQUEST, "ese nombre de usuario ya
    // existe");
    // if(usuarioRepository.existsByEmail(nuevoUsuario.getEmail()))
    // throw new CustomException(HttpStatus.BAD_REQUEST, "ese email de usuario ya
    // existe");
    // Usuario usuario =
    // new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
    // nuevoUsuario.getEmail(),
    // passwordEncoder.encode(nuevoUsuario.getPassword()));
    // Set<Rol> roles = new HashSet<>();
    // roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    // if(nuevoUsuario.getRoles().contains("admin"))
    // roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
    // usuario.setRoles(roles);
    // usuarioRepository.save(usuario);
    // return new Mensaje(usuario.getNombreUsuario() + " ha sido creado");
    // }

    // Metodo para crear usuario unicamente con el rol de ROLE_USER
    public Mensaje registroUsuario(NuevoUsuario nuevoUsuario) {
        if (usuarioRepository.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese nombre de usuario ya existe");
        if (usuarioRepository.existsByEmail(nuevoUsuario.getEmail()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese email de usuario ya existe");
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),nuevoUsuario.getTelefono(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()), true);
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
        return new Mensaje(usuario.getNombreUsuario() + " ha sido creado");
    }

    // metodo para listar usuarios por rol
    public List<ListarUsuarioPorRol> listarUsuariosPorRol() {
        List<Object[]> results = usuarioRepository.listarUsuariosPorRol();
        return results.stream()
                .map(result -> new ListarUsuarioPorRol((int) result[0], (String) result[1], (String) result[2], (int) result[3],
                        (Boolean) result[4]))
                .collect(Collectors.toList());
    }

    // en vez de eliminar el usuario, se cambia su estado a false
    public Optional<Usuario> desactivarUsuario(int idUsuario, boolean estado) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {

            if (estado == false) {
                usuario.get().setEstado(false);
            } else {
                usuario.get().setEstado(true);
            }
            usuarioRepository.save(usuario.get());
            return usuario;

        } else {
            return Optional.empty();
        }
    }

    // creamos un metodo para guardar un usuario dependiendo de que rol se le asigne
    public Mensaje guardarUsuario(NuevoUsuario nuevoUsuario, String tipoRol) {
        if (usuarioRepository.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese nombre de usuario ya existe");
        }
        if (usuarioRepository.existsByEmail(nuevoUsuario.getEmail())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese email ya está registrado");
        }

        Usuario usuario = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getTelefono(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()),
                true);

        Set<Rol> roles = new HashSet<>();

        // utilizamos en switch para asignar el rol correspondiente
        switch (tipoRol) {
            case "ADMINISTRADOR":
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
                break;
            case "ROLE_USER":
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
                break;
            case "OPERADOR":
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_OPERADOR).get());
                break;
            case "GERENTE":
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_GERENTE).get());
                break;
            default:
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_REPARTIDOR).get());
                break;
        }

        usuario.setRoles(roles);

        usuarioRepository.save(usuario);

        return new Mensaje(usuario.getNombreUsuario() + " ha sido creado");

    }

}
