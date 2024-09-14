package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarea;
    private String nombre;
    private String descripcion;
    private String color;
    
}
