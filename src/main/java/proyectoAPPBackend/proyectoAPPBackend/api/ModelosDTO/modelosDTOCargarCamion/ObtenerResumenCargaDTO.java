package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOCargarCamion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerResumenCargaDTO {

    private String nombreProducto;
    private int cantidadLLenos;
    private int cantidadVacios;
    
}
