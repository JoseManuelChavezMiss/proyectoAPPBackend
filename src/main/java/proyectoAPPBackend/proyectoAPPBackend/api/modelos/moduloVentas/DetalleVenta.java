package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloVentas;

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
public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalleVenta;

    private int cantidad;

    private BigDecimal precio;

    @JoinColumn(name = "idVenta")
    @ManyToOne(optional = false)
    private Venta venta;

    @JoinColumn(name = "idProducto")
    @ManyToOne(optional = false)
    private Producto producto;
   
}
