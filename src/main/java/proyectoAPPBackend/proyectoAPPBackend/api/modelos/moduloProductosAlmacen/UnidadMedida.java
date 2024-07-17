package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen;

import jakarta.persistence.Column;
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
public class UnidadMedida {
    
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idUnidadMedida;
    private String nombre;

    
}
