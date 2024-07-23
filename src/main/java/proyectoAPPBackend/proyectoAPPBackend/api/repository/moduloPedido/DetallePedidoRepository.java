package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    
}
