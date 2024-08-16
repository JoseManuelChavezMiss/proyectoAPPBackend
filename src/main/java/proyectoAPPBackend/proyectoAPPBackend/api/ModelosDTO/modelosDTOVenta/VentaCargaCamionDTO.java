package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaCargaCamionDTO {

    private int idCargarCamion;
    private int cantidadLLenos;
    private int cantidadVacios;
    private int idProducto;
    private String nombreProducto;
    private BigDecimal precio;
 
}
