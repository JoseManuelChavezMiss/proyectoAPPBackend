package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignarTareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   
    @ManyToOne
    @JoinColumn(name = "idTarea", nullable = false)
    private Tareas tareas;  // Relación con la tabla de tareas

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;  // Relación con la tabla de usuarios

    private String periodicidad;  // Diario, Semanal, etc.

    private LocalDate fechaInicio;  // Fecha de inicio de la asignación

    private String estado;  // Asignado, En Proceso, Terminada

    @OneToMany(mappedBy = "asignarTareas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AsignarTareasDetalle> detalles;  // Relación con los detalles de la tarea
}
