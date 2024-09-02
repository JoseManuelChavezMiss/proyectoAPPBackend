package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloPedidosController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.PedidoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.PedidosPendientesDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.productoPedidoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloPedido.PedidoService;

@RestController
@RequestMapping("/pedidos")
//@CrossOrigin( origins = "*")
@CrossOrigin( origins = "https://aguasanta.store/")
public class PedidosController {

    @Autowired
    PedidoService pedidoService;

    //metodo para crear un pedido
    @PostMapping("/crear")
    public ResponseEntity<Mensaje> crearPedido(@RequestBody List<productoPedidoDTO> productos){
        try {
            pedidoService.crearPedido(productos);
            return new ResponseEntity<>(new Mensaje("Pedido creado correctamente"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al crear el pedido"), HttpStatus.BAD_REQUEST);
        }
        
    }

    //metodo para obtener los pedidos pendientes de un usuario
    // @GetMapping("/pendientes/{idUsuario}")
    // public List<Pedido> obtenerPedidosPendientes(@PathVariable int idUsuario){
    //     return pedidoService.listarPedidosPendientes(idUsuario);
    // }

    //metodo para listar los pedidos dto
    @GetMapping("/listar/{idUsuario}")
    public List<PedidoDTO> listarPedidosDTO(@PathVariable int idUsuario){
        return pedidoService.listarPedidosDTO(idUsuario);
    }

    @GetMapping("/pendientes")
    public List<PedidosPendientesDTO> listarPedidosPendientes() {
        return pedidoService.listarPedidosPendientes();
    }

    
}
