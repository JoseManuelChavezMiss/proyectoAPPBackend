package proyectoAPPBackend.proyectoAPPBackend.security.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyectoAPPBackend.proyectoAPPBackend.security.entity.Rol;
import proyectoAPPBackend.proyectoAPPBackend.security.enums.RolNombre;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    //metodo para buscar el nombre del rol
    Optional<Rol> findByRolNombre(RolNombre rolNombre);

    @Procedure(procedureName = "sp_actualizarRolYTelefonoUsuario")
    void actualizarRolYTelefonoUsuario(@Param("idUsuario") Integer idUsuario, 
                                       @Param("idRol") Integer idRol, 
                                       @Param("nuevoTelefono") Integer nuevoTelefono);
    


}
