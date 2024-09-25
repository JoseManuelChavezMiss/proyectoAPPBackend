package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleTareasUsuarioDTO {
    private int idDetalleTarea;
    private String nombreUsuario;
    private String fechaEjecucion;
    private String color;
    private String nombreTarea;
    private String descripcionTarea;
    private String estado;

    
}
