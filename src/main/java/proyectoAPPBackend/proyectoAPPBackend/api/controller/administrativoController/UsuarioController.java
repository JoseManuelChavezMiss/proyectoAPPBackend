package proyectoAPPBackend.proyectoAPPBackend.api.controller.administrativoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.ListarUsuarioPorRol;
import proyectoAPPBackend.proyectoAPPBackend.security.dto.NuevoUsuario;
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

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id, boolean estado){
        usuarioService.desactivarUsuario(id, estado);
        return ResponseEntity.ok(new Mensaje("Usuario eliminado correctamente"));
    }

    //guardar usuario
    @PostMapping("/guardarUsuario")
    public ResponseEntity<Mensaje> guardarUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario,String rol){
        return ResponseEntity.ok(usuarioService.guardarUsuario(nuevoUsuario,rol));
    }
    
    
}
