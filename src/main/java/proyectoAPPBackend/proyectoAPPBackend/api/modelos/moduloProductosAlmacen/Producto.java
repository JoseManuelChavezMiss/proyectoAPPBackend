package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen;

import java.math.BigDecimal;

import jakarta.persistence.Column;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String nombre;
    private String descripcion;
    @JoinColumn(name = "idUnidadMedida")
    @ManyToOne(optional = false)
    private UnidadMedida unidadMedida;
    private BigDecimal precio;
    private String imagenUrl;
    
    @Column(nullable = false)
    private boolean retornable = false;
    
}
