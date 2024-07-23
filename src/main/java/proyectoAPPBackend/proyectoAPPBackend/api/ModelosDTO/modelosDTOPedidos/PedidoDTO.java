package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private int idPedido;
    private String numeroPedido;
    private String fechaPedido;
    private String telefono;
    private BigDecimal total;
    private String nombreUsuario;
    private String direccion;
    private String estado;
    private List<DetallePedidoDTO> detalles; 
    
}
