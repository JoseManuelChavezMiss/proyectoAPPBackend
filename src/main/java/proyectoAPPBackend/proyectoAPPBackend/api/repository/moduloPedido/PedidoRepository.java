package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    //metodo para buscar los pedidos por el id del usuario que realizo el pedido pendiente
    // List<Pedido> findByUsuarioIdAndEstado(int usuarioId, String estado);
    List<Pedido> findByUsuarioIdAndEstado(int usuarioId, String estado);

    @Query(value = "CALL sp_obtenerPedidosPendientes()", nativeQuery = true)
    List<Object[]> listarPedidosPendientes();
    
}
