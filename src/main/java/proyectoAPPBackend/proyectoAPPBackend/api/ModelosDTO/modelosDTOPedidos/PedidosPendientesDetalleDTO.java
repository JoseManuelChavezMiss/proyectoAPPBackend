package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidosPendientesDetalleDTO {
    
    private String fechaPedido;
    private int idPedido;
    private double totalVenta;
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private double precio;
    private int idCliente;
    private int idRepartidor;
}
