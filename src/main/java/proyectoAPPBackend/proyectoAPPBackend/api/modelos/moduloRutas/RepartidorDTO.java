package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepartidorDTO {

    private Long idRepartidor;
    private String nombre;
    private String nombreUsuario;
    private String rolNombre;
    private Boolean estado;
    
}
