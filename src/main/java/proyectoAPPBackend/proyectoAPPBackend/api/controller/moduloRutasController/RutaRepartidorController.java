package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloRutasController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.rutaRepartidor;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloRutas.RutaRepartidorService;

@RestController
@RequestMapping("/rutaRepartidor")
@CrossOrigin(origins = "*")
public class RutaRepartidorController {

    @Autowired
    RutaRepartidorService rutaRepartidorService;

    // metodo para listar los repartidores
    @GetMapping("/listarRepartidores")
    public Object listarRepartidores() {
        return rutaRepartidorService.listarRepartidoresActivos();
    }

    //metodo para guardar una ruta al repartidor
    @PostMapping("/guardarRutaRepartidor")
    public ResponseEntity<Mensaje> guardarRutaRepartidor(@RequestBody rutaRepartidor rutaRepartidor) {
        try {
            rutaRepartidorService.guardarRutaRepartidor(rutaRepartidor);
            return ResponseEntity.ok(new Mensaje("Ruta asignada correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Mensaje(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Mensaje("Error al asignar la ruta"));
        }
       
    }

    //metodo para listar las rutas asignadas a un repartidor
    @GetMapping("/listarRutasRepartidor/Asignadas")
    public List<rutaRepartidor> listarRutasRepartidor() {
        return rutaRepartidorService.listarRutasRepartidor();
    }

    //metodo para modificar una ruta asignada a un repartidor
    @PutMapping("/modificarRutaRepartidor")
    public ResponseEntity<Mensaje> modificarRutaRepartidor(@RequestBody rutaRepartidor rutaRepartidor) {
        try {
            System.out.println(rutaRepartidor.getVehiculo().getIdVehiculo());
            rutaRepartidorService.modificarRutaRepartidor(rutaRepartidor);
            return ResponseEntity.ok(new Mensaje("Ruta modificada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Mensaje("Error al modificar la ruta"));
        }
    }

    
    
}
