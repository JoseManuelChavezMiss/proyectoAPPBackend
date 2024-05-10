package proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.clases;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailValues {

    private String  emailFrom;
    private String emailTo;
    private String subject;
    private String nombreUsuario;
    private String jwt;   
}
