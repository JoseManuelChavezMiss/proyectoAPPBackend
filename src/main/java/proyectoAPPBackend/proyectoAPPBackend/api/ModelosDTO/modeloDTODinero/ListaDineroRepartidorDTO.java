package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTODinero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListaDineroRepartidorDTO {
    private int idCarga;
    private double montoEntregado;
    private double totalVentas;
    private double totalEntregar;
    
}
