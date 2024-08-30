package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloPedidosController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.AsignacionPedidoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.AsignacionPedido;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloPedido.AsignarPedidoService;

@RestController
@RequestMapping("/asignarPedido")
@CrossOrigin(origins = "*")
// @CrossOrigin( origins = "http://aguasanta.store/")
public class AsignarPedidoController {

    @Autowired
    AsignarPedidoService asignarPedidoService;

    // metodo para asignar un pedido a un repartidor
    @PostMapping("/asignar")
    public ResponseEntity<Mensaje> asignarPedido(@RequestBody AsignacionPedidoDTO asignacionPedidoDTO) {
        asignarPedidoService.asignarPedido(asignacionPedidoDTO.getIdRepartidor(), asignacionPedidoDTO.getIdPedido(), asignacionPedidoDTO.getFechaAsignacion());
        return new ResponseEntity<>(new Mensaje("Pedido asignado correctamente"), HttpStatus.OK);
    }

}
