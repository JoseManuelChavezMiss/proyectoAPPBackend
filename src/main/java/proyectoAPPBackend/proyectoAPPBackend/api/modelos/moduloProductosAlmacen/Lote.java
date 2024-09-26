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
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLote;
    @JoinColumn(name = "idProducto")
    @ManyToOne(optional = false)
    private Producto Producto;

    @JoinColumn(name = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario Usuario;

    private String fechaProduccion;
    private Integer cantidadProduccion;
    private String observaciones;
    

    
}
