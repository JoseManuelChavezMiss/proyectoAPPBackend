package proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailValuesDTO {

    private String  emailFrom;
    private String emailTo;
    private String subject;
    private String nombreUsuario;
    private String jwt;   
}
