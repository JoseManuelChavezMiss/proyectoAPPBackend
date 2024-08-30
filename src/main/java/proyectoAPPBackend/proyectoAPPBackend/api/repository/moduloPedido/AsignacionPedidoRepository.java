package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.AsignacionPedido;

@Repository
public interface AsignacionPedidoRepository extends JpaRepository<AsignacionPedido, Integer> {
    
}
