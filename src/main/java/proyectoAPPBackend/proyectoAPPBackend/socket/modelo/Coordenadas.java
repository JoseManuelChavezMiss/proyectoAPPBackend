package proyectoAPPBackend.proyectoAPPBackend.socket.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordenadas {

    String latitud;
    String longitud;
    String idRepartidor;
    String telefono;
    String nombreUsuario;
    String rolUsuario;

    
}
