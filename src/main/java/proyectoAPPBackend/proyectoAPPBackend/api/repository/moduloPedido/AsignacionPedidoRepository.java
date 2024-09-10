package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.AsignacionPedido;

@Repository
public interface AsignacionPedidoRepository extends JpaRepository<AsignacionPedido, Integer> {

    
    @Query(value = "CALL sp_listarAsignacionePedidosRepartidor(:opcion,:idRepartidor)", nativeQuery = true)
    List<Object[]> listarAsignacionePedidosRepartidor(int opcion, int idRepartidor);

    @Query(value = "CALL sp_obtenerPedidoDetalle(:idPedido)", nativeQuery = true)
    List<Object[]> obtenerPedidoDetalle(int idPedido);
    
}
