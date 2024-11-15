package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.UnidadMedida;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer> {

    //buscar por id
    UnidadMedida findById(int idUnidadMedida);
    Optional<UnidadMedida> findByIdUnidadMedida(int idAlmacen);    
}
