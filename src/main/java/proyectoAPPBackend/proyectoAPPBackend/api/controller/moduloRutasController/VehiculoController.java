package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloRutasController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.Vehiculo;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloRutas.VehiculoService;

@RestController
@RequestMapping("/vehiculos")
//@CrossOrigin(origins = "*")
@CrossOrigin( origins = "https://aguasanta.store/")
public class VehiculoController {

    @Autowired
    VehiculoService vehiculoService;

    // metodo para listar los vehiculos
    @GetMapping("/listar")
    public List<Vehiculo> listarVehiculos() {
        return vehiculoService.listarVehiculos();
    }

    //Metodo para guardar un vehiculo
    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarVehiculo(@Valid @RequestBody Vehiculo vehiculo) {
        try {

            if(vehiculo.getCapacidadAlmacenaje() <= 0){
                return ResponseEntity.badRequest().body(new Mensaje("No puede ingresar un valor menor o igual a 0 en la capacidad de almacenaje"));
            }else{
                
            vehiculoService.guardarVehiculo(vehiculo);
            }

            return ResponseEntity.ok(new Mensaje("Vehiculo guardado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Mensaje("Error al guardar el vehiculo"));
        }
    }

    //Metodo para eliminar un vehiculo
    @DeleteMapping("/eliminar/{idVehiculo}")
    public ResponseEntity<Mensaje> eliminarVehiculo(@PathVariable int idVehiculo) {
        try {
            vehiculoService.eliminarVehiculo(idVehiculo);
            return ResponseEntity.ok(new Mensaje("Vehiculo eliminado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Mensaje("Error al eliminar el vehiculo esta asociado a un repartidor"));
        }
    }

    //Metodo para modificar un vehiculo
    @PutMapping("/modificar")
    public ResponseEntity<Mensaje> modificarVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            if(vehiculo.getCapacidadAlmacenaje() <= 0){
                return ResponseEntity.badRequest().body(new Mensaje("No puede ingresar un valor menor o igual a 0 en la capacidad de almacenaje"));
            }else{
                vehiculoService.modificarVehiculo(vehiculo); 
            }
            
            return ResponseEntity.ok(new Mensaje("Vehiculo modificado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Mensaje("Error al modificar el vehiculo"));
        }
    }

    
}
