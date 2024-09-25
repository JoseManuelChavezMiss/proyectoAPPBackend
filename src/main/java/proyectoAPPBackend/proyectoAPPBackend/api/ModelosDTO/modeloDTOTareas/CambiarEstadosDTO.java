package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CambiarEstadosDTO {

    private int idDetalle;
    private int idUsuario;
    private String direccion;
    
}
