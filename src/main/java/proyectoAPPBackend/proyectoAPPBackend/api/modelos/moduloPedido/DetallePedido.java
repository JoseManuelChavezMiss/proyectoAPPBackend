package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido;

import java.math.BigDecimal;

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
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetallePedido;
    @JoinColumn(name = "idPedido")
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "idProducto")
    @ManyToOne(optional = false)
    private Producto producto;
    private int cantidad;
    private BigDecimal precio;
  
    
}
