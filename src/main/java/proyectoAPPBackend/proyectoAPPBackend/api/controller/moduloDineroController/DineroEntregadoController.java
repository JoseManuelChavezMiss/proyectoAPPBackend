package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloDineroController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTODinero.NuevoIngresoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloDinero.DineroEntregadoService;

@RestController
@RequestMapping("/dineroEntregado")
@CrossOrigin(origins = "*")
public class DineroEntregadoController {

    @Autowired
    DineroEntregadoService dineroEntregadoService;

    // Metodo para guardar el dinero entregado
    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarDineroEntregado(@RequestBody NuevoIngresoDTO nuevoIngresoDTO) {
        try {
            dineroEntregadoService.guardarDineroEntregado(nuevoIngresoDTO);
            return new ResponseEntity<>(new Mensaje("Dinero entregado guardado correctamente"), HttpStatus.OK);
        } catch (RuntimeException e) {
            // Manejo de excepciones específicas de la aplicación
            return new ResponseEntity<>(new Mensaje("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción no controlada
            return new ResponseEntity<>(new Mensaje("Error al guardar el dinero entregado: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Netodo para listar la carga del repartidor
    @GetMapping("/listarCargaRepartidor")
    public ResponseEntity<?> listarCargaRepartidor() {
        try {
            return new ResponseEntity<>(dineroEntregadoService.listarCargaRepartidor(), HttpStatus.OK);
        } catch (RuntimeException e) {
            // Manejo de excepciones específicas de la aplicación
            return new ResponseEntity<>(new Mensaje("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción no controlada
            return new ResponseEntity<>(new Mensaje("Error al listar la carga del repartidor: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // metodo para entregar el dinero entregado
    @GetMapping("/listar")
    public ResponseEntity<?> listarDineroEntregado() {
        try {
            return new ResponseEntity<>(dineroEntregadoService.listarDineroEntregado(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Mensaje("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al listar el dinero entregado: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("dineroRepartidor/{idUsuario}")
    public ResponseEntity<?> listarDineroRepartidor(@PathVariable("idUsuario") int idUsuario) {
        try {
            return new ResponseEntity<>(dineroEntregadoService.obtenerDineroVentasRepartidor(idUsuario), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Mensaje("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al listar el dinero del repartidor: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodo para completar la carga del repartidor 
    @PostMapping("/completarCargaRepartidorDescargarcamion/{idCarga}")
    public ResponseEntity<Mensaje> completarCargaRepartidorDescargarCamion(@PathVariable("idCarga") int idCarga) {
        try {
            dineroEntregadoService.completarCargaDescargarCamion(idCarga);
            return new ResponseEntity<>(new Mensaje("Carga completada correctamente"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Mensaje("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al completar la carga del repartidor: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
