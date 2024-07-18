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
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLote;
    @JoinColumn(name = "idProducto")
    @ManyToOne(optional = false)
    private Producto Producto;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String fechaProduccion;
    private Integer cantidadProduccion;
    private String observaciones;
    

    
}
