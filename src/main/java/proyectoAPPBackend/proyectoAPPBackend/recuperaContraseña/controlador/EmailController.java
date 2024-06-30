package proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.controlador;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.dto.EmailValuesDTO;
import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.dto.cambiarContraseñaDTO;
import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.servicios.EmailService;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.service.UsuarioService;

@RestController
@RequestMapping("/email")
@CrossOrigin
public class EmailController {

    // inyectamos el servicio de emial
    @Autowired
    EmailService emailService;

    // inyectamos el serviciio de usuario
    @Autowired
    UsuarioService usuarioService;

    //
    @Autowired
    PasswordEncoder passwordEncoder;

    // metodo para enviar el correo con la plantilla html
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/enviarEmail")
    public ResponseEntity<?> enviarEmailHtml(@RequestBody EmailValuesDTO emailValues) {

        Optional<Usuario> usuarioOpt = usuarioService.getByNombreUsuarioOrEmail(emailValues.getEmailTo());

        if (!usuarioOpt.isPresent()) {
            return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"),
                    HttpStatus.NOT_FOUND);
        } else {

            Usuario usuario = usuarioOpt.get();

            emailValues.setEmailFrom("aguapurasantaana@gmail.com");
            emailValues.setSubject("Recuperación contraseña");
            emailValues.setNombreUsuario(usuario.getNombreUsuario());
            emailValues.setEmailTo(usuario.getEmail());
            UUID uuid = UUID.randomUUID();
            emailValues.setJwt(uuid.toString());
            usuario.setTokenPassword(uuid.toString());
            usuarioService.save(usuario);
            emailService.enviarEmailTemplate(emailValues);
            // System.out.println("Correo enviado"+emailService);
            return new ResponseEntity(
                    new Mensaje("Te hemos enviado un correo con las instrucciones para recuperar tu contraseña"),
                    HttpStatus.NOT_FOUND);

        }

    }

    //metodo para recuperar la contraseña
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/recuperarContraseña")
    public ResponseEntity<?> recuperarContraseña (@Valid @RequestBody cambiarContraseñaDTO cambiarContraseñaDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Campos incorrectos o email no válido"), HttpStatus.BAD_REQUEST);
        }
        else if(!cambiarContraseñaDTO.getPassword().equals(cambiarContraseñaDTO.getConfirmaPassword())){
            return new ResponseEntity(new Mensaje("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
        }else{
            Optional<Usuario> usuarioOpt = usuarioService.getByTokenPassword(cambiarContraseñaDTO.getTokenPassword());
            if (!usuarioOpt.isPresent()) {
                return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"),
                        HttpStatus.NOT_FOUND);
            }else{
                Usuario usuario = usuarioOpt.get();
                String nuevoPassword = passwordEncoder.encode(cambiarContraseñaDTO.getPassword());
                usuario.setPassword(nuevoPassword);
                usuario.setTokenPassword(null);
                usuarioService.save(usuario);

                return new ResponseEntity(new Mensaje("Contraseña actualizada correctamente"),
                        HttpStatus.NOT_FOUND);
               
            }


        }
       
    }
}
