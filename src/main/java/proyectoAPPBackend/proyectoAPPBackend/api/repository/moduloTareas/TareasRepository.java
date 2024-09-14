package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.Tareas;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
    
    @Query(value = "CALL sp_listarTareasPendientesGeneral()", nativeQuery = true)
    List<Object[]> listarTareasPendientesGeneral();

}
