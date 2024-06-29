package proyectoAPPBackend.proyectoAPPBackend.security.repository;

import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByNombreUsuarioOrEmail(String nombreUsuario, String email);
    Optional<Usuario> findByTokenPassword(String tokenPassword);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
    //verificar el estado del usuario para el login
    boolean existsByNombreUsuarioAndEstado(String nombreUsuario, boolean estado);
    boolean existsByEmailAndEstado(String email, boolean estado);

    //ejecutamos un procedimiento almacenado
    @Query(value = "CALL sp_listarUsuariosPorRol()", nativeQuery = true)
    // List<ListarUsuarioPorRol> listarUsuariosPorRol();
    List<Object[]> listarUsuariosPorRol();

    //envez de eliminar el usuario  solo pasamos el estado a false


}
