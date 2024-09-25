package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloTareas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloTareas.AsignarTareas;

@Repository
public interface AsignarTareasRepository extends JpaRepository<AsignarTareas, Integer> {

    @Query(value = "CALL sp_obtenerTareasAsignadas()", nativeQuery = true)
    List<Object[]> obtenerTareasAsignadas();

    @Query(value = "CALL sp_obtenerDetalleTareasUsuario(:idUsuario)", nativeQuery = true)
    List<Object[]> obtenerDetalleTareasUsuario(Integer idUsuario);

    @Query(value = "CALL sp_cambiarEstadoTareaAsignada(:idDetelle,:idUsuario,:direccion)", nativeQuery = true)
    void cambiarEstadoTareaAsignada(Integer idDetelle, Integer idUsuario, String direccion);



  
    
}
