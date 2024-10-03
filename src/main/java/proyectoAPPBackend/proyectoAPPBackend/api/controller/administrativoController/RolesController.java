package proyectoAPPBackend.proyectoAPPBackend.api.controller.administrativoController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOAdministrativo.actualizarRolesDTO;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Rol;
import proyectoAPPBackend.proyectoAPPBackend.security.service.RolService;


@RestController
@RequestMapping("/roles")
//@CrossOrigin( origins = "*")
@CrossOrigin( origins = "https://aguasanta.store/")
public class RolesController {

    @Autowired
    RolService  rolService;
     
    //lista los roles y usa un switch para transformar los nombres de los roles
    @GetMapping("/listarRoles")
    public List<Rol> listarRoles() {
        return rolService.listarRoles();
    }

    //Actualiza el rol de un usuario
    @PostMapping("/actualizarRolUsuario")
    public void actualizarRolUsuario(@RequestBody actualizarRolesDTO actualizarRolesDTO){
        rolService.actualizarRolUsuario(actualizarRolesDTO.getIdUsuario(), actualizarRolesDTO.getIdRol(), actualizarRolesDTO.getTelefono());
    }


    // @Value("${media.location}")
    // private String ubicacion;
   
    
}
