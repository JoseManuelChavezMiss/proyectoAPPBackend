package proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloRutas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.rutaRepartidor;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;

@Repository
public interface RutaRepartidorRepository extends JpaRepository<rutaRepartidor, Integer> {


    //listamos los repartidores
    @Query(value = "CALL listarRepartidoresActivos()", nativeQuery = true)
    List<Object[]> listarRepartidoresActivos();

    //busca por usuario
    Optional<rutaRepartidor> findByUsuario(Usuario usuario);

    List<rutaRepartidor> findByUsuarioId(int idUsuario);
    
}
