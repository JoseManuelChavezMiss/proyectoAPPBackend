package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTODinero;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListaDineroEntregadoDTO {

    private int idDineroEntregado;
    private int idUsuario;
    private String repartidor;
    private double montoEntregado;
    private LocalDateTime fechaEntrega;
    private boolean entregado;


    
}
