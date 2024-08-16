package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaListaUsuariosDTO {

    private int idUsuario;
    private String email;
    private String nombre;
    private String nombreUsuario;
    private Boolean estado;

    
}
