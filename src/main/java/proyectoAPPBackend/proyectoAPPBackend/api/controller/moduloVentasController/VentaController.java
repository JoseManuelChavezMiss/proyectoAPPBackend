package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloVentasController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta.DetalleVentaDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloVentas.VentaService;

@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = "*")
//@CrossOrigin( origins = "https://aguasanta.store/")
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
     
    @PostMapping("/generarVenta")
    public ResponseEntity<Mensaje> cargarCamion(@RequestBody List<DetalleVentaDTO>  venta) {
        try {
            ventaService.generarVenta(venta);
            return ResponseEntity.ok(new Mensaje("Venta realizada correctamente"));
        } catch (RuntimeException e) {
            // Manejo de excepciones específicas de la aplicación
            return ResponseEntity.badRequest().body(new Mensaje("Error: " + e.getMessage()));
        } catch (Exception e) {
            // Manejo de cualquier otra excepción no controlada
            return ResponseEntity.internalServerError().body(new Mensaje("Error al realizar la venta: " + e.getMessage()));
        }
    }
}
