package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByIdProducto(long idProducto);

    //obtener el precio de un producto por su id
    Optional<Producto> findByPrecio(long idProducto);
    
}
