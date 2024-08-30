package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloPedido;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.AsignacionPedido;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.Pedido;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido.AsignacionPedidoRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido.PedidoRepository;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.UsuarioRepository;

@Service
@Transactional
public class AsignarPedidoService {

    @Autowired
    AsignacionPedidoRepository asignacionPedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    //Metodo para asignar un pedido a un repartidor
    
    public void asignarPedido(int idRepartidor, int idPedido, Date fechaAsignacion) {
        AsignacionPedido asignacionPedido = new AsignacionPedido();
        Usuario repartidor = buscarUsuarioPorId(idRepartidor);
        Pedido pedido = buscarPedidoPorId(idPedido);

        asignacionPedido.setRepartidor(repartidor);
        asignacionPedido.setPedido(pedido);
        asignacionPedido.setFechaAsignacion(fechaAsignacion);

        // Cambiar el estado del pedido a "Asignado"
        pedido.setEstado("Asignado");

        // Guardar la asignación del pedido
        asignacionPedidoRepository.save(asignacionPedido);

        // Guardar el pedido actualizado
        pedidoRepository.save(pedido);
    }
    // public void asignarPedido(int idRepartidor, int idPedido, Date fechaAsignacion) {
    //     // creamos un objeto asignacionPedido y le asignamos los valores
    //     AsignacionPedido asignacionPedido = new AsignacionPedido();
    //     asignacionPedido.setRepartidor(buscarUsuarioPorId(idRepartidor));
    //     asignacionPedido.setPedido(buscarPedidoPorId(idPedido));
    //     asignacionPedido.setFechaAsignacion(fechaAsignacion);

    //     // guardamos la asignacion del pedido
    //     asignacionPedidoRepository.save(asignacionPedido);
    // }

    // metodo para encontrar el usuario por su id
    public Usuario buscarUsuarioPorId(int idUsuario) {
        // si el usuairo no existe retornamos usuario no encontrado
        if (!usuarioRepository.existsById(idUsuario)) {
            return null;
        } else {
            // si el usuario existe retornamos el usuario
            return usuarioRepository.findById(idUsuario).get();
        }
    }

    // metodo para encontrar el pedido por su id
    public Pedido buscarPedidoPorId(int idPedido) {
        // si el pedido no existe retornamos pedido no encontrado
        if (!pedidoRepository.existsById(idPedido)) {
            return null;
        } else {
            // si el pedido existe retornamos el pedido
            return pedidoRepository.findById(idPedido).get();
        }
    }
    
}
