package proyectoAPPBackend.proyectoAPPBackend.security.service;

import proyectoAPPBackend.proyectoAPPBackend.security.entity.Rol;
import proyectoAPPBackend.proyectoAPPBackend.security.enums.RolNombre;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
    
    //lista los roles
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

     //metodo para actualizar el rol de un usuario
     public void actualizarRolUsuario(Integer idUsuario, Integer idRol){
        System.out.println("idUsuario: "+idUsuario+" idRol: "+idRol);
        rolRepository.actualizarRolUsuario(idUsuario, idRol);
    }
    



}
