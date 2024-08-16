package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloRutas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.RepartidorDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.rutaRepartidor;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloRutas.RutaRepartidorRepository;

@Service
@Transactional
public class RutaRepartidorService {

    @Autowired
    RutaRepartidorRepository rutaRepartidorRepository;
    
    //metodo para listar los repartidores activos
    public List<RepartidorDTO> listarRepartidoresActivos() {
        List<Object[]> resultados = rutaRepartidorRepository.listarRepartidoresActivos();
        List<RepartidorDTO> repartidores = new ArrayList<>();

        for (Object[] resultado : resultados) {
            RepartidorDTO dto = new RepartidorDTO();
            dto.setIdRepartidor(((Number) resultado[0]).longValue());
            dto.setNombre((String) resultado[1]);
            dto.setNombreUsuario((String) resultado[2]);
            dto.setRolNombre((String) resultado[3]);
            dto.setEstado((Boolean) resultado[4]);
            repartidores.add(dto);
        }

        return repartidores;
    }

    //metodo para guardar una ruta al repartidor
    public void guardarRutaRepartidor(rutaRepartidor RutaRepartidor) {
        Optional<rutaRepartidor> asignacionExistente = rutaRepartidorRepository.findByUsuario(RutaRepartidor.getUsuario());
        if (asignacionExistente.isPresent()) {
            throw new RuntimeException("El usuario ya tiene una asignación existente. Elimine la asignación antes de crear una nueva.");
        }else{
            rutaRepartidorRepository.save(RutaRepartidor);
        }
    }

    public void modificarRutaRepartidor(rutaRepartidor RutaRepartidor) {
        rutaRepartidor rutaRepartidorModificado = rutaRepartidorRepository.findById(RutaRepartidor.getIdRutaRepartidor()).get();
        rutaRepartidorModificado.setUsuario(RutaRepartidor.getUsuario());
        rutaRepartidorModificado.setVehiculo(RutaRepartidor.getVehiculo());
        rutaRepartidorModificado.setRuta(RutaRepartidor.getRuta());
        rutaRepartidorRepository.save(rutaRepartidorModificado);
    }

    //metodo para eliminar una ruta asignada a un repartidor
    public void eliminarRutaRepartidor(int idRutaRepartidor) {
        rutaRepartidorRepository.deleteById(idRutaRepartidor);
    }


    //metodo para listar las rutas asignadas a un repartidor
    public List<rutaRepartidor> listarRutasRepartidor() {
        return rutaRepartidorRepository.findAll();
    }

    //metodo para lista rutas asignadas a un repartidor por su id
    public List<rutaRepartidor> buscarRutaRepartidorId(int idUsuario) {
        return rutaRepartidorRepository.findByUsuarioId(idUsuario);
    }
        
    
}
