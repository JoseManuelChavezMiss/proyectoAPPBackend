package proyectoAPPBackend.proyectoAPPBackend.api.controller.administrativoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.security.dto.ListarUsuarioPorRol;
import proyectoAPPBackend.proyectoAPPBackend.security.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
@CrossOrigin( origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listarUsuariosPorRol")
    public ResponseEntity<List<ListarUsuarioPorRol>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuariosPorRol());
    }
    
}
