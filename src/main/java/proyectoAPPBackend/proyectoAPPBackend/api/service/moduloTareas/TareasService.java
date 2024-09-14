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

    //metodo para crear una tareas
    public void guardarTarea(Tareas tarea) {
        tareasRepository.save(tarea);
    }

    //Metodo para lisrar las tareas
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

    
}
