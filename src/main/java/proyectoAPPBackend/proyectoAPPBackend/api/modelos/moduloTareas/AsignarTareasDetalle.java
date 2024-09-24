package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignarTareasDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "asignar_tareas_id")
    private AsignarTareas asignarTareas;  // Relación con la tarea principal

    private LocalDate fechaEjecucion;  // Fecha en que se debe ejecutar la tarea

    @ManyToOne
    @JoinColumn(name = "id_estados_tarea")
    private EstadosTarea estadosTarea;  // Estado de la tarea
}
