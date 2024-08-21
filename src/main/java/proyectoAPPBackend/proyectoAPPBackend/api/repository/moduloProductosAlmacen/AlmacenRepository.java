package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer>{

    //para buscar por id
    Almacen findById(int idAlmacen);
    Optional<Almacen> findByIdAlmacen(int idAlmacen);

    // List<Almacen> findByProductoIdProducto(Long idProducto);
    List<Almacen> findByIdAlmacen(Long productoId);
    
    
    
}
