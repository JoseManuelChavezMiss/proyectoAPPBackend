package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TareasAsignadasDTO {

    private int idTareaAsignada;
    private String fechaInicio;
    private String periodicidad;
    private String nombreTarea;
    private String nombreUsuario;
    
    
}
