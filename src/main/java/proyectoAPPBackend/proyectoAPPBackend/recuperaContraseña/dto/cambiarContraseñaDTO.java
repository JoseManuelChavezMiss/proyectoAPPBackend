package proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class cambiarContraseñaDTO {
    
    @NotBlank
    private String password;
    @NotBlank
    private String confirmaPassword;
    @NotBlank
    private String tokenPassword;


    
}
