package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloVentas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloVentas.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query(value = "CALL ListarUsuariosPorRolVenta(:valor)", nativeQuery = true)
    List<Object[]> listarUsuariosPorRolVenta(int valor);

    @Query(value = "CALL sp_obtenerCargasCamionDelDiaPorUsuario(:idUsuario)", nativeQuery = true)
    List<Object[]> obtenerCargasCamionDelDiaPorUsuario(int idUsuario);

    @Query(value = "SELECT verificarExistenciasRepartidor(:p_id_usuario, :p_id_producto)", nativeQuery = true)
    Boolean verificarExistenciasRepartidor(int p_id_usuario, int p_id_producto);
}
