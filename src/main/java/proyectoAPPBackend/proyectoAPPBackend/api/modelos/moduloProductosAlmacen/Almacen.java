package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Almacen {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idAlmacen;
    private String nombre;
    private int capacidad;
    private int cantidad;
    
}
