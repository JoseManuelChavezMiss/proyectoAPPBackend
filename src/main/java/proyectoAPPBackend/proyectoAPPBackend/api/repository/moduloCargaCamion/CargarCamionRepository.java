package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloCargaCamion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.CargarCamion;

@Repository
public interface CargarCamionRepository extends JpaRepository<CargarCamion, Integer> {

}
