package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloCargaCamion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.DetalleCarga;

@Repository
public interface DetalleCargaRepository extends JpaRepository<DetalleCarga, Integer> {

    //obtener el detalle de la carga a partir de id_cargar_camion
    List<DetalleCarga> findByCargarCamionIdCargarCamion(int idCargarCamion);

    //listamos los el detalle de la carga a partir de id_cargar_camion
    @Query(value = "CALL sp_ObtenerCargasPendientesPorUsuario(:p_id_usuario)", nativeQuery = true)
    List<Object[]> obtenerCargasPendientePorUsuario(int p_id_usuario);
}
