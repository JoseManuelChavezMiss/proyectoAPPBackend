package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloTareas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas.AsignarTareasDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas.DetalleTareasUsuarioDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas.TareasAsignadasDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.AsignarTareas;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.AsignarTareasDetalle;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.Tareas;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas.AsignarTareasDetalleRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas.AsignarTareasRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas.EstadosTareasRepository;
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

    @Autowired
    EstadosTareasRepository estadosTareasRepository;

    public String asignarTareaConDetalles(AsignarTareasDTO request) {
        // Crear la asignación de tarea principal
        AsignarTareas asignacion = new AsignarTareas();
        asignacion.setTareas(buscarTareaPorId(request.getIdTarea()));
        asignacion.setUsuario(buscarUsuarioPorId(request.getIdUsuario()));
        asignacion.setFechaInicio(request.getFechaInicio());
        asignacion.setPeriodicidad(request.getPeriodicidad());

        // Guardar la asignación principal
        AsignarTareas asignacionGuardada = asignarTareasRepository.save(asignacion);

        // Crear los detalles de las tareas recurrentes
        List<AsignarTareasDetalle> detalles = new ArrayList<>();
        LocalDate fechaActual = request.getFechaInicio();
        int cantidadRepeticiones = calcularRepeticiones(request.getPeriodicidad());

        for (int i = 0; i < cantidadRepeticiones; i++) {
            // Verificar si se debe incluir o excluir los sábados y domingos
            if ((!request.getIncluirSabados() && fechaActual.getDayOfWeek() == DayOfWeek.SATURDAY) ||
                    (!request.getIncluirDomingos() && fechaActual.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                // Si es sábado o domingo y no debe ser incluido, se omite y se pasa a la
                // siguiente fecha
                fechaActual = calcularProximaFecha(fechaActual, request.getPeriodicidad());
                continue;
            }

            AsignarTareasDetalle detalle = new AsignarTareasDetalle();
            detalle.setAsignarTareas(asignacionGuardada);
            detalle.setFechaEjecucion(fechaActual);
            detalle.setEstadosTarea(estadosTareasRepository.findById(1).get());
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
            case "quincenal":
            case "mensual":
            case "trimestral":
            case "semestral":
            case "anual":
                // Si la fecha actual cae en sábado o domingo, moverla al siguiente lunes
                if (fechaActual.getDayOfWeek() == DayOfWeek.SATURDAY
                        || fechaActual.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    return fechaActual.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                }
                return incrementarFechaPorPeriodicidad(fechaActual, periodicidad);
            default:
                throw new IllegalArgumentException("Periodicidad no válida");
        }
    }

    private LocalDate incrementarFechaPorPeriodicidad(LocalDate fechaActual, String periodicidad) {
        switch (periodicidad.toLowerCase()) {
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

    //Metodo para listar todas las tareas asignadas
    public List<TareasAsignadasDTO> listarTareasAsignadasUsuario() {
        List<Object[]> resultados = asignarTareasRepository.obtenerTareasAsignadas();
        List<TareasAsignadasDTO> tareas = new ArrayList<>();

        for (Object[] resultado : resultados) {
            TareasAsignadasDTO dto = new TareasAsignadasDTO();
            dto.setIdTareaAsignada((int) resultado[0]);
            // dto.setFechaInicio((String) resultado[1]);
            dto.setFechaInicio(resultado[1].toString());
            dto.setPeriodicidad((String) resultado[2]);
            dto.setNombreTarea((String) resultado[3]);
            dto.setNombreUsuario((String) resultado[4]);
            tareas.add(dto);
        }
        return tareas;
    }

    //metodo para listar detalle de las tareas asignadas a un usuario
    public List<DetalleTareasUsuarioDTO> obtenerDetalleTareasUsuario(int idUsuario){
        List<Object[]> resultados = asignarTareasRepository.obtenerDetalleTareasUsuario(idUsuario);
        List<DetalleTareasUsuarioDTO> tareas = new ArrayList<>();

        for (Object[] resultado : resultados) {
            DetalleTareasUsuarioDTO dto = new DetalleTareasUsuarioDTO();
            dto.setIdDetalleTarea((int) resultado[0]);
            dto.setNombreUsuario((String) resultado[1]);
            dto.setFechaEjecucion(resultado[2].toString());
            dto.setColor((String) resultado[3]);
            dto.setNombreTarea((String) resultado[4]);
            dto.setDescripcionTarea((String) resultado[5]);
            dto.setEstado((String) resultado[6]);
            tareas.add(dto);
        }
        return tareas;
        
    }

    //Metodo para eliminar una tarea asignada
    public String eliminarTareaAsignada(int idTareaAsignada) {
        if (!asignarTareasRepository.existsById(idTareaAsignada)) {
            return "Tarea no encontrada";
        } else {
            asignarTareasRepository.deleteById(idTareaAsignada);
            return "Tarea eliminada";
        }
    }

    //Metodo para cambiar el estado de las tareas Asignado, En Proceso, Finalizado
    public void cambiarEstadoTarea(Integer idDetelle, Integer idUsuario, String direccion) {
       asignarTareasRepository.cambiarEstadoTareaAsignada(idDetelle, idUsuario, direccion); 
    }

}
