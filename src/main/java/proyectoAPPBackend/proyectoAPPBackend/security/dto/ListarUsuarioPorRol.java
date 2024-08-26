package proyectoAPPBackend.proyectoAPPBackend.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListarUsuarioPorRol {

    private int id;
    private String nombre;
    private String rol_nombre;
    private int telefono;
    private boolean estado;
}
