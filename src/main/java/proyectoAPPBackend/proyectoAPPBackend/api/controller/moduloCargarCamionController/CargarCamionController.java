package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloCargarCamionController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.DetalleCargaDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloCargaCamion.CargarCamionService;

@RestController
@RequestMapping("/cargarCamion")
// @CrossOrigin( origins = "*")
@CrossOrigin( origins = "http://aguasanta.store/")
public class CargarCamionController {

    @Autowired
    CargarCamionService cargarCamionService;

    //metodo para cargar un camion
    @PostMapping("/cargar")
    public ResponseEntity<Mensaje> cargarCamion(@RequestBody List<DetalleCargaDTO> carga){
        try {
            cargarCamionService.guardarCargaCamion(carga);
            return new ResponseEntity<>(new Mensaje("Camion cargado correctamente"), HttpStatus.OK);
        } catch (RuntimeException e) {
            // Manejo de excepciones específicas de la aplicación
            return new ResponseEntity<>(new Mensaje("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción no controlada
            return new ResponseEntity<>(new Mensaje("Error al cargar pedido: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
}
