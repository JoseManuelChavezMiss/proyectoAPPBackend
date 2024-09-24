package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloTareas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas.AsignarTareasDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas.TareasPendientesGeneralDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.Tareas;
import org.springframework.web.bind.annotation.RequestBody;

import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloTareas.AsignarTareasService;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloTareas.TareasService;

@RestController
@RequestMapping("/tarea")
@CrossOrigin(origins = "*")
public class TareasController {

    @Autowired
    TareasService tareasService;

    @Autowired
    AsignarTareasService asginarTareasService;

    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarTarea(@RequestBody Tareas tarea) {
        try {
            tareasService.guardarTarea(tarea);
            return ResponseEntity.ok(new Mensaje("Tarea guardada correctamente"));
        } catch (RuntimeException e) {
            // Manejo de excepciones específicas de la aplicación
            return ResponseEntity.badRequest().body(new Mensaje("Error: " + e.getMessage()));
        } catch (Exception e) {
            // Manejo de cualquier otra excepción no controlada
            return ResponseEntity.internalServerError()
                    .body(new Mensaje("Error al guardar la tarea: " + e.getMessage()));
        }
    }

    // metodo para listar las tareas
    @GetMapping("/listar")
    public List<Tareas> listarTareas() {
        return tareasService.listarTareas();
    }

    @PostMapping("/asignarTarea")
    public ResponseEntity<?> asignarTarea(@RequestBody AsignarTareasDTO tarea) {
        try {
            // Llamamos al servicio para asignar la tarea y obtener el mensaje de éxito
            String mensaje = asginarTareasService.asignarTareaConDetalles(tarea);
    
            // Verificamos si el mensaje está vacío o nulo (opcional, dependiendo de tu lógica)
            if (mensaje == null || mensaje.isEmpty()) {
                return ResponseEntity.badRequest().body(new Mensaje("No se pudo asignar la tarea"));
            }
    
            // Devolvemos el mensaje de éxito
            return ResponseEntity.ok(new Mensaje(mensaje));
    
        } catch (RuntimeException e) {
            // Manejo de excepciones específicas de la aplicación
            return ResponseEntity.badRequest().body(new Mensaje("Error: " + e.getMessage()));
        } catch (Exception e) {
            // Manejo de cualquier otra excepción no controlada
            return ResponseEntity.internalServerError()
                    .body(new Mensaje("Error al asignar la tarea: " + e.getMessage()));
        }
    }
    

    // metodo para listar las tareas asignadas
    @GetMapping("/listarAsignadas")
    public List<TareasPendientesGeneralDTO> listarAsignarTareas() {
        return tareasService.listarTareasPendientesGeneral();
    }

}