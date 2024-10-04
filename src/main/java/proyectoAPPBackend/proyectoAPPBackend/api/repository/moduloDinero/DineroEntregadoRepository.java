package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloDinero;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloDinero.DineroEntregado;

@Repository
public interface DineroEntregadoRepository extends JpaRepository<DineroEntregado, Integer> {

    @Query(value = "CALL sp_listaCargaRepartidor()", nativeQuery = true)
    List<Object[]> listaCargaRepartidor();

    @Query(value = "CALL sp_listarDineroEntregado()", nativeQuery = true)
    List<Object[]> listaDineroEntregado();

    @Query(value = "CALL sp_obtenerDineroVentasRepartidor(:idUsuario)", nativeQuery = true)
    List<Object[]> obtenerDineroVentasRepartidor(int idUsuario);

    @Query(value = "CALL sp_completarCargaDescargarCamion(:idCarga)", nativeQuery = true)
    void completarCargaDescargarCamion(int idCarga);

}
