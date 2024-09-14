package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloTareas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas.AsignarTareasDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.AsignarTareas;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.AsignarTareasDetalle;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.Tareas;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas.AsignarTareasDetalleRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas.AsignarTareasRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas.TareasRepository;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.UsuarioRepository;

@Service
@Transactional
public class AsignarTareasService {

    @Autowired
    AsignarTareasRepository asignarTareasRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TareasRepository tareasRepository;

    @Autowired
    AsignarTareasDetalleRepository asignarTareasDetalleRepository;

    public String asignarTareaConDetalles(AsignarTareasDTO request) {
        // Crear la asignación de tarea principal
        AsignarTareas asignacion = new AsignarTareas();
        System.out.println("ID TAREA: " + request.getIdTarea());
        System.out.println("ID USUARIO: " + request.getIdUsuario());
        asignacion.setTareas(buscarTareaPorId(request.getIdTarea()));
        asignacion.setUsuario(buscarUsuarioPorId(request.getIdUsuario()));
        asignacion.setFechaInicio(request.getFechaInicio());
        asignacion.setPeriodicidad(request.getPeriodicidad());
        asignacion.setEstado("Asignado");
    
        // Guardar la asignación principal
        AsignarTareas asignacionGuardada = asignarTareasRepository.save(asignacion);
    
        // Crear los detalles de las tareas recurrentes
        List<AsignarTareasDetalle> detalles = new ArrayList<>();
        LocalDate fechaActual = request.getFechaInicio();
        int cantidadRepeticiones = calcularRepeticiones(request.getPeriodicidad());
    
        for (int i = 0; i < cantidadRepeticiones; i++) {
            AsignarTareasDetalle detalle = new AsignarTareasDetalle();
            detalle.setAsignarTareas(asignacionGuardada);
            detalle.setFechaEjecucion(fechaActual);
            detalle.setEstado("Asignado");
            detalles.add(detalle);
    
            // Incrementar la fecha según la periodicidad
            fechaActual = calcularProximaFecha(fechaActual, request.getPeriodicidad());
        }
    
        // Guardar los detalles de la tarea
        asignarTareasDetalleRepository.saveAll(detalles);
    
        // Retornar el mensaje de éxito
        return "Excelente, guardado";
    }
    

    // MEMTODO PARA LISTAR LAS TAREAS ASIGNADAS
    public List<AsignarTareas> listarAsignarTareas() {
        return asignarTareasRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
        // si el usuairo no existe retornamos usuario no encontrado
        if (!usuarioRepository.existsById(idUsuario)) {
            return null;
        } else {
            // si el usuario existe retornamos el usuario
            return usuarioRepository.findById(idUsuario).get();
        }
    }

    public Tareas buscarTareaPorId(int idTarea) {
        // si la tarea no existe retornamos tarea no encontrada
        if (!tareasRepository.existsById(idTarea)) {
            return null;
        } else {
            // si la tarea existe retornamos la tarea
            return tareasRepository.findById(idTarea).get();
        }
    }

    private int calcularRepeticiones(String periodicidad) {
        switch (periodicidad.toLowerCase()) {
            case "diario":
                return 365; // Para un año de tareas
            case "semanal":
                return 52;
            case "quincenal":
                return 24;
            case "mensual":
                return 12;
            case "trimestral":
                return 4;
            case "semestral":
                return 2;
            case "anual":
                return 1;
            default:
                throw new IllegalArgumentException("Periodicidad no válida");
        }
    }

    private LocalDate calcularProximaFecha(LocalDate fechaActual, String periodicidad) {
        switch (periodicidad.toLowerCase()) {
            case "diario":
                return fechaActual.plus(1, ChronoUnit.DAYS);
            case "semanal":
                return fechaActual.plus(1, ChronoUnit.WEEKS);
            case "quincenal":
                return fechaActual.plus(2, ChronoUnit.WEEKS);
            case "mensual":
                return fechaActual.plus(1, ChronoUnit.MONTHS);
            case "trimestral":
                return fechaActual.plus(3, ChronoUnit.MONTHS);
            case "semestral":
                return fechaActual.plus(6, ChronoUnit.MONTHS);
            case "anual":
                return fechaActual.plus(1, ChronoUnit.YEARS);
            default:
                throw new IllegalArgumentException("Periodicidad no válida");
        }

    }

}
