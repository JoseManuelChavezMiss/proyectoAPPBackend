package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
}
