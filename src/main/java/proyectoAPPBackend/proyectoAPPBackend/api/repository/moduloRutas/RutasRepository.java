package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloRutas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.Ruta;

@Repository
public interface RutasRepository extends JpaRepository<Ruta, Integer> {
    
}
