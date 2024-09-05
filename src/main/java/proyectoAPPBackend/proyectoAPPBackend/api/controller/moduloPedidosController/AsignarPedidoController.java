package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloPedidosController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.AsignacionPedidoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.AsignacionPedidosListaDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloPedido.AsignarPedidoService;

@RestController
@RequestMapping("/asignarPedido")
@CrossOrigin(origins = "*")
//@CrossOrigin( origins = "https://aguasanta.store/")
public class AsignarPedidoController {

    @Autowired
    AsignarPedidoService asignarPedidoService;

    // metodo para asignar un pedido a un repartidor
    @PostMapping("/asignar")
    public ResponseEntity<Mensaje> asignarPedido(@RequestBody AsignacionPedidoDTO asignacionPedidoDTO) {
        asignarPedidoService.asignarPedido(asignacionPedidoDTO.getIdRepartidor(), asignacionPedidoDTO.getIdPedido(), new java.util.Date());
        return new ResponseEntity<>(new Mensaje("Pedido asignado correctamente"), HttpStatus.OK);
    }

    //metodo para listar
    @GetMapping("/listarPedidosRepartidor/{idRepartidor}/{opcion}")
    public List<AsignacionPedidosListaDTO> listarPedidosRepartidor(@PathVariable int idRepartidor, @PathVariable int opcion) {
        return asignarPedidoService.listarPedidosAsignados(idRepartidor, opcion);
    }

    @DeleteMapping("/eliminarAsignacionPedido/{idAsignacionPedido}")
    public ResponseEntity<Mensaje> eliminarAsignacionPedido(@PathVariable int idAsignacionPedido) {
        asignarPedidoService.eliminarAsignacionPedido(idAsignacionPedido);
        return new ResponseEntity<>(new Mensaje("Asignación eliminada correctamente"), HttpStatus.OK);
    }

}
