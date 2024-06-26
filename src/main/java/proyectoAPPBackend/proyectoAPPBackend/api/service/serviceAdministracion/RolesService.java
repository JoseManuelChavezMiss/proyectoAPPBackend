package proyectoAPPBackend.proyectoAPPBackend.api.service.serviceAdministracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Rol;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.RolRepository;


@Service
@Transactional
public class RolesService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    RolRepository rolRepository;
    
     //ejecutamos un procedimiento almacenado
     @SuppressWarnings("unchecked")
    public List<Rol> listarRoles() {
         // Llama al procedimiento almacenado sin usar @Query
         return entityManager.createStoredProcedureQuery("sp_listarRoles", Rol.class).getResultList();
     }

    







}
