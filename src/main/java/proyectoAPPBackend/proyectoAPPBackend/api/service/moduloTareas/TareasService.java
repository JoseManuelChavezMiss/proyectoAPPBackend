package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloTareas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas.TareasPendientesGeneralDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.Tareas;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas.TareasRepository;

@Service
@Transactional
public class TareasService {

    @Autowired
    TareasRepository tareasRepository;

    // metodo para crear una tareas
    public void guardarTarea(Tareas tarea) {
        // verificar si el color de la tarea ya existe
        if (tareasRepository.findByColor(tarea.getColor()) != null) {
            throw new RuntimeException("El color de la tarea ya existe");
        } else {
            tareasRepository.save(tarea);
        }
    }

    // Metodo para lisrar las tareas
    public List<Tareas> listarTareas() {
        return tareasRepository.findAll();
    }

    public List<TareasPendientesGeneralDTO> listarTareasPendientesGeneral() {
        List<Object[]> resultados = tareasRepository.listarTareasPendientesGeneral();
        List<TareasPendientesGeneralDTO> tareas = new ArrayList<>();

        // Formato para las fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] resultado : resultados) {
            TareasPendientesGeneralDTO dto = new TareasPendientesGeneralDTO();
            dto.setNombreUsuario((String) resultado[0]);
            dto.setNombreTarea((String) resultado[1]);
            dto.setDescripcionTarea((String) resultado[2]);
            dto.setColorTarea((String) resultado[3]);

            // Aquí convertimos la fecha
            LocalDate fechaEjecucion = ((java.sql.Date) resultado[4]).toLocalDate();
            dto.setFechaEjecucion(fechaEjecucion.format(formatter));

            dto.setEstado((String) resultado[5]);
            tareas.add(dto);
        }
        return tareas;
    }

    // metodo para eliminar una tarea
    public void eliminarTarea(Integer idTarea) {
        tareasRepository.deleteById(idTarea);
    }

    // metodo para modificar una tarea
    // public void modificarTarea(Tareas tarea) {
    //     // verificar si el color de la tarea ya existe
    //     if (tareasRepository.findByColor(tarea.getColor()) != null) {
    //         throw new RuntimeException("El color de la tarea ya existe");
    //     } else {
    //         tareasRepository.save(tarea);
    //     }
    // }

    public void modificarTarea(Tareas tarea) {
        // Obtener la tarea existente por su ID
        Tareas tareaExistente = tareasRepository.findById(tarea.getIdTarea()).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    
        // Verificar si el color de la tarea ya existe en otra tarea
        Tareas tareaConMismoColor = tareasRepository.findByColor(tarea.getColor());
        if (tareaConMismoColor != null && tareaConMismoColor.getIdTarea() != tarea.getIdTarea()) {
            // Si el color ya existe en otra tarea, mantener el color existente
            tarea.setColor(tareaExistente.getColor());
        }

        System.out.println("Tarea: " + tarea);
    
        // Actualizar otros atributos de la tarea existente
        tareaExistente.setNombre(tarea.getNombre());
        tareaExistente.setDescripcion(tarea.getDescripcion());
        tareaExistente.setColor(tarea.getColor());
    
        // Guardar la tarea modificada
        tareasRepository.save(tareaExistente);
    }

}
