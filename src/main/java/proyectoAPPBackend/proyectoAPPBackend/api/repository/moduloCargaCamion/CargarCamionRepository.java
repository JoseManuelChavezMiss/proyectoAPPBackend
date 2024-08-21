package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloCargaCamion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.CargarCamion;

@Repository
public interface CargarCamionRepository extends JpaRepository<CargarCamion, Integer> {

     @Query(value = "CALL sp_descargarcamion(:idUsuario)", nativeQuery = true)
     Object descargarCamion(int idUsuario);

     @Query(value = "SELECT verificarCargaCreadaRepartidor(:idUsuario)", nativeQuery = true)
     Boolean verificarCargaCreadaRepartidor(@Param("idUsuario") int idUsuario);


}