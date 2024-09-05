package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.AsignacionPedido;

@Repository
public interface AsignacionPedidoRepository extends JpaRepository<AsignacionPedido, Integer> {

    
    @Query(value = "CALL sp_listarAsignacionePedidosRepartidor(:idRepartidor,:opcion)", nativeQuery = true)
    List<Object[]> listarAsignacionePedidosRepartidor(int idRepartidor, int opcion);
    
}
