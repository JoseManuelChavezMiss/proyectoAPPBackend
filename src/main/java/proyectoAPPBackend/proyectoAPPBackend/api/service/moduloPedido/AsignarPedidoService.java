package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloPedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.AsignacionPedidosListaDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.PedidosPendientesDetalleDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.PedidosPendientesRepartidorDTO;
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

    //listar pedidos asignados para ver los pedidos asignados a los repartidores
    public List<AsignacionPedidosListaDTO> listarPedidosAsignados(int opcion, int idRepartidor) {
        List<AsignacionPedidosListaDTO> asignaciones = new ArrayList<>();
            List<Object[]> resultados = asignacionPedidoRepository.listarAsignacionePedidosRepartidor(opcion, idRepartidor);
    
            for (Object[] resultado : resultados) {
                AsignacionPedidosListaDTO asignacionDTO = new AsignacionPedidosListaDTO();
    
                // Asignación en el orden especificado en la clase DTO
                asignacionDTO.setIdAsignacionPedido(((Number) resultado[0]).intValue());
                asignacionDTO.setIdPedido(((Number) resultado[1]).intValue());
                asignacionDTO.setFechaAsignacion(resultado[2].toString());
                asignacionDTO.setNumeroPedido(resultado[3].toString());
                asignacionDTO.setNombreCliente(resultado[4].toString());
                asignacionDTO.setNombreUsuarioCliente(resultado[5].toString());
                asignacionDTO.setDireccionCliente(resultado[6].toString());
                asignacionDTO.setTelefonoCliente(resultado[7].toString());
                asignacionDTO.setTotalProductos(((Number) resultado[8]).intValue());
                asignacionDTO.setTotalPedido(((Number) resultado[9]).doubleValue());
                asignacionDTO.setNombreRepartidor(resultado[10].toString());
    
                asignaciones.add(asignacionDTO);
            }
    
        return asignaciones;
    }

    //listar pedidos asignados a los repartidores
        public List<PedidosPendientesRepartidorDTO> listarPedidosPendientesRepartidor(int opcion, int idRepartidor) {
        List<PedidosPendientesRepartidorDTO> pedidosPendientes = new ArrayList<>();
        List<Object[]> resultados = asignacionPedidoRepository.listarAsignacionePedidosRepartidor(opcion, idRepartidor);
    
        for (Object[] resultado : resultados) {
            PedidosPendientesRepartidorDTO pedidoPendiente = new PedidosPendientesRepartidorDTO();
    
            // Asignación en el orden especificado en la clase DTO
            pedidoPendiente.setIdPedido(((Number) resultado[0]).intValue());
            pedidoPendiente.setFechaAsignacion(resultado[1].toString());
            pedidoPendiente.setNumeroPedido(resultado[2].toString());
            pedidoPendiente.setNombreCliente(resultado[3].toString());
            pedidoPendiente.setNombreUsuarioCliente(resultado[4].toString());
            pedidoPendiente.setDireccionCliente(resultado[5].toString());
            pedidoPendiente.setTelefonoCliente(resultado[6].toString());
            pedidoPendiente.setTotalProductos(((Number) resultado[7]).intValue());
            pedidoPendiente.setTotalPedido(((Number) resultado[8]).doubleValue());
    
            pedidosPendientes.add(pedidoPendiente);
        }
    
        return pedidosPendientes;
    }

    public List<PedidosPendientesDetalleDTO> listarPedidosDetallePendientes(int idPedido) {
        List<PedidosPendientesDetalleDTO> pedidosPendientes = new ArrayList<>();
        List<Object[]> resultados = asignacionPedidoRepository.obtenerPedidoDetalle(idPedido);

        for (Object[] resultado : resultados) {
            PedidosPendientesDetalleDTO pedidoPendiente = new PedidosPendientesDetalleDTO();

            // Asignación en el orden especificado en la clase DTO
            pedidoPendiente.setIdPedido(((Number) resultado[0]).intValue());
            pedidoPendiente.setFechaPedido(resultado[1].toString());
            pedidoPendiente.setTotalVenta(((Number) resultado[2]).doubleValue());
            pedidoPendiente.setIdProducto(((Number) resultado[3]).intValue());
            pedidoPendiente.setNombreProducto(resultado[4].toString());
            pedidoPendiente.setCantidad(((Number) resultado[5]).intValue());
            pedidoPendiente.setPrecio(((Number) resultado[6]).doubleValue());
            pedidoPendiente.setIdCliente(((Number) resultado[7]).intValue());
            pedidoPendiente.setIdRepartidor(((Number) resultado[8]).intValue());

            pedidosPendientes.add(pedidoPendiente);
        }

        return pedidosPendientes;
    }

    //metodo para eliminar la asignacion de un pedido y cambiar el estado del pedido a 'Pendiente'
    public void eliminarAsignacionPedido(int idAsignacionPedido) {
        AsignacionPedido asignacionPedido = asignacionPedidoRepository.findById(idAsignacionPedido).get();
        Pedido pedido = asignacionPedido.getPedido();

        // Cambiar el estado del pedido a "Pendiente"
        pedido.setEstado("Pendiente");

        // Guardar el pedido actualizado
        pedidoRepository.save(pedido);

        // Eliminar la asignación del pedido
        asignacionPedidoRepository.deleteById(idAsignacionPedido);
    }
    
    
}
