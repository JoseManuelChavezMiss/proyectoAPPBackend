package proyectoAPPBackend.proyectoAPPBackend.socket.controlador;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import proyectoAPPBackend.proyectoAPPBackend.socket.modelo.Coordenadas;

@Controller
public class WebSocketController {

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public Coordenadas chat(@DestinationVariable String roomId, Coordenadas message) {
        // Validar si el rol es ROLE_REPARTIDOR
        if ("ROLE_REPARTIDOR".equals(message.getRolUsuario())) {
            // Si idRepartidor es null, no enviar el mensaje
            if (message.getIdRepartidor() == null) {
                return null; // No enviar nada
            }
            // Retornar el objeto completo si idRepartidor no es null
            System.out.println(message);
            return new Coordenadas(message.getLatitud(), message.getLongitud(), message.getIdRepartidor(), message.getTelefono(), message.getNombreUsuario(), message.getRolUsuario());
        }
        // Si el rol no es ROLE_REPARTIDOR, no enviar nada
        return null;
    }
}

// public class WebSocketController {
//     @MessageMapping("/chat/{roomId}")
//     @SendTo("/topic/{roomId}")
//     public Coordenadas chat(@DestinationVariable String roomId, Coordenadas message) {
//         System.out.println(message);
//         return new Coordenadas(message.getLatitud(), message.getLongitud(), message.getIdRepartidor(),message.getTelefono(), message.getNombreUsuario(), message.getRolUsuario());
//     }
// }