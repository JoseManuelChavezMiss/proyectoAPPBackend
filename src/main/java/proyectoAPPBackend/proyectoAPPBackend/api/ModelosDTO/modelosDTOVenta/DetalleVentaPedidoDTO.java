package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaPedidoDTO {

    private Date fechaVenta;
    private int totalVenta;
    private int idProducto;
    private int cantidad;
    private int idCliente;
    private int idRepartidor;
    private int idPedido;
    
}
