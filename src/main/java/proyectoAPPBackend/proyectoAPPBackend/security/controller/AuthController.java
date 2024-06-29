package proyectoAPPBackend.proyectoAPPBackend.security.controller;

import jakarta.validation.Valid;
import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.JwtDto;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.ListarUsuarioPorRol;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.LoginUsuario;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.NuevoUsuario;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Rol;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.service.RolService;
import proyectoAPPBackend.proyectoAPPBackend.security.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/auth")
//@CrossOrigin( origins = "http://localhost:4200/")
@CrossOrigin( origins = "*")
public class AuthController {

    @Autowired
    UsuarioService usuarioService;
    

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario){
        return ResponseEntity.ok(usuarioService.login(loginUsuario));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        return ResponseEntity.ok(usuarioService.refresh(jwtDto));
    }

    //Metodo post para registro de usuario con ROLE_USER

    @PostMapping("/registroUsuario")
    public ResponseEntity<Mensaje> registroUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario){
        return ResponseEntity.ok(usuarioService.registroUsuario(nuevoUsuario));
    }

    

    
}
