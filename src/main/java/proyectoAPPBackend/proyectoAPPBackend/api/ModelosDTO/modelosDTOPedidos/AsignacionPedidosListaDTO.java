package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionPedidosListaDTO {

    private int idAsignacionPedido;
    private int idPedido;
    private String fechaAsignacion;
    private String numeroPedido;
    private String nombreCliente;
    private String nombreUsuarioCliente;
    private String direccionCliente;
    private String telefonoCliente;
    private int totalProductos;
    private double totalPedido;
    private String nombreRepartidor;
}