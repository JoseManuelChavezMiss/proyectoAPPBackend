package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloVentasController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloVentas.VentaService;

@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = "*")
//@CrossOrigin( origins = "http://aguasanta.store/")
public class VentaController {

    @Autowired
    VentaService ventaService;

    //hacemos un controlador para listar los usuarios por rol de venta la cual le pasaremos un valor
    @GetMapping("/listarUsuariosPorRolVenta/{valor}")
    public Object listarUsuariosPorRolVenta(@PathVariable("valor") int valor) {
        return ventaService.listarUsuariosPorRolVenta(valor);
    }
    
    //hacemos un controlador para listar las cargas de camion del dia por usuario
    @GetMapping("/listarCargaCamionRepartidor/{idUsuario}")
    public Object listarCargaCamionRepartidor(@PathVariable("idUsuario") int idUsuario) {
        return ventaService.listarCargaCamionRepartidor(idUsuario);
    }
}
