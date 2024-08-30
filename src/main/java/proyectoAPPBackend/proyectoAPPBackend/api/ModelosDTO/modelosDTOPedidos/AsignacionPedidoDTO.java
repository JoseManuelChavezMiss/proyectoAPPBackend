package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionPedidoDTO {

    private int idPedido;
    private int idRepartidor;
    private Date fechaAsignacion;
    
}
