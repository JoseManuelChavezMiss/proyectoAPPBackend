package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloRutasController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.Ruta;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloRutas.RutasService;

@RestController
@RequestMapping("/rutas")
// @CrossOrigin(origins = "*")
@CrossOrigin( origins = "http://aguasanta.store/")
public class RutasController {

    @Autowired
    RutasService rutasService;

    // metodo para listar las rutas
    @GetMapping("/listar")
    public List<Ruta> listarRutas() {
        return rutasService.listarRutas();
    }

    // metodo para guardar una ruta
    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarRuta(@RequestBody Ruta ruta) {
        try {
            rutasService.guardarRuta(ruta);
            return new ResponseEntity<>(new Mensaje("Ruta guardada correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al guardar la ruta"), HttpStatus.BAD_REQUEST);
        }
    }

    // metodo para modificar una ruta solo utilizamos los setters de la clase Ruta
    @PutMapping("/modificar/{idRuta}")
    public ResponseEntity<Mensaje> modificarRuta(@RequestBody Ruta ruta, @PathVariable int idRuta) {
        try {
            Ruta rutaActual = rutasService.findById(idRuta);
            if (rutaActual == null) {
                return new ResponseEntity<>(new Mensaje("Ruta no encontrada"), HttpStatus.NOT_FOUND);
            } else {
                rutaActual.setNombre(ruta.getNombre());
                rutaActual.setDescripcion(ruta.getDescripcion());
                rutasService.guardarRuta(rutaActual);
                return new ResponseEntity<>(new Mensaje("Ruta actualizada con éxito"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al modificar la ruta"), HttpStatus.BAD_REQUEST);
        }
    }

    // metodo para eliminar una ruta
    @DeleteMapping("/eliminar/{idRuta}")
    public ResponseEntity<Mensaje> eliminarRuta(@PathVariable int idRuta) {
        try {
            rutasService.eliminarRuta(idRuta);
            return new ResponseEntity<>(new Mensaje("Ruta eliminada con éxito"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al eliminar la ruta"), HttpStatus.BAD_REQUEST);
        }
    }
}
