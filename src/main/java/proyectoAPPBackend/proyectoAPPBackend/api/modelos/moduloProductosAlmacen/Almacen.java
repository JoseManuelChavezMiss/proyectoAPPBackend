package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen;


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
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlmacen;
    @JoinColumn(name = "idProducto")
    @ManyToOne(optional = false)
    private Producto Producto;
    private Integer cantidadDisponible;
    
}
