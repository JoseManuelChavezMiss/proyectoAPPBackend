package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.UnidadMedida;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class productoPedidoDTO {
    
    private int idUsuario;
    private int idProducto;
    private String nombre;
    private UnidadMedida unidadMedida;
    private BigDecimal precio;
    private String imagenUrl;
    private int cantidad;
    private double totalProducto;
    private double totalPedido;
    private String telefono;
    private String direccion;
    
    
}
