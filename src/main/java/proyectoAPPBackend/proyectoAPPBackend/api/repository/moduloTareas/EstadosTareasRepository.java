package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.EstadosTarea;

@Repository
public interface EstadosTareasRepository extends JpaRepository<EstadosTarea, Integer> {
    
}
