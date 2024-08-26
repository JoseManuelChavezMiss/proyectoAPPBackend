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
        System.out.println(message);
        return new Coordenadas(message.getLatitud(), message.getLongitud(), message.getIdRepartidor(),message.getTelefono(), message.getNombreUsuario());
    }
}