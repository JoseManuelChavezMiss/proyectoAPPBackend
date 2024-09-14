package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TareasPendientesGeneralDTO {
    private String nombreUsuario;
    private String nombreTarea;
    private String descripcionTarea;
    private String colorTarea;
    private String fechaEjecucion;
    private String estado;  
}
