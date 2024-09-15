package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTOTareas;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AsignarTareasDTO {

    private int idTarea;
    private int idUsuario;
    private LocalDate fechaInicio;
    private String periodicidad;
    private Boolean incluirSabados;
    private Boolean incluirDomingos;
    
}
