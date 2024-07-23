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
public class DetallePedidoDTO {

    private int idDetallePedido;
    private int cantidad;
    private BigDecimal precio;
    private List<ProductoDTO> productos; 


}
