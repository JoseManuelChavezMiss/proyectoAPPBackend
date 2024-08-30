package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidosPendientesDTO {
    
    private int idPedido;
    private String cliente;
    private String numeroPedido;
    private String direccion;
    private String telefono;
    private int totalProductos;
    private int totalUnidades;
}
