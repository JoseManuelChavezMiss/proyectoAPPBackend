package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.AsignarTareasDetalle;

@Repository
public interface AsignarTareasDetalleRepository extends JpaRepository<AsignarTareasDetalle, Integer> {
    
}
