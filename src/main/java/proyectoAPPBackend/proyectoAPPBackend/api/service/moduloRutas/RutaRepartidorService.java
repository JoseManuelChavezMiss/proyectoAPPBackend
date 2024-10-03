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
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloRutas.VehiculoRepository;

@Service
@Transactional
public class RutaRepartidorService {

    @Autowired
    RutaRepartidorRepository rutaRepartidorRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    // metodo para listar los repartidores activos
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

    // metodo para guardar una ruta al repartidor
    public void guardarRutaRepartidor(rutaRepartidor RutaRepartidor) {
        Optional<rutaRepartidor> asignacionExistente = rutaRepartidorRepository
                .findByUsuario(RutaRepartidor.getUsuario());
        if (asignacionExistente.isPresent()) {
            throw new RuntimeException(
                    "El usuario ya tiene una asignación existente. Elimine la asignación antes de crear una nueva.");
        } else {
            // List<rutaRepartidor> asignacionExistenteVehiculo = rutaRepartidorRepository
            // .findByVehiculo(RutaRepartidor.getVehiculo());
            // if (!asignacionExistenteVehiculo.isEmpty()) {
            // throw new RuntimeException(
            // "El vehiculo ya tiene una asignación existente. Elimine la asignación antes
            // de crear una nueva.");
            // } else {
            // rutaRepartidorRepository.save(RutaRepartidor);
            // }

            // verificar si el vehiculo ya tiene una ruta asignada
            Optional<rutaRepartidor> asignacionExistenteVehiculo = rutaRepartidorRepository
                    .findByVehiculo(RutaRepartidor.getVehiculo());
            if (asignacionExistenteVehiculo.isPresent()) {
                throw new RuntimeException(
                        "El vehiculo ya tiene una asignación existente.Elimine la asignación antes de crear una nueva.");
            } else {
                rutaRepartidorRepository.save(RutaRepartidor);
            }

        }
    }

    public void modificarRutaRepartidor(rutaRepartidor RutaRepartidor) {
        // Obtener la ruta actual del repartidor
        rutaRepartidor rutaActual = rutaRepartidorRepository.findById(RutaRepartidor.getIdRutaRepartidor())
                .orElseThrow(() -> new RuntimeException("No se encontró la ruta especificada."));

        // Verificar si el vehículo que se quiere asignar ya tiene una ruta asignada
        Optional<rutaRepartidor> asignacionExistenteVehiculo = rutaRepartidorRepository
                .findByVehiculo(RutaRepartidor.getVehiculo());

        // Si el vehículo ya tiene asignación y no es el mismo vehículo que ya tenía el
        // repartidor
        if (asignacionExistenteVehiculo.isPresent() && !asignacionExistenteVehiculo.get().getUsuario()
                .equals(rutaActual.getUsuario())) {
            throw new RuntimeException("El vehículo ya está asignado a otro repartidor.");
        }

        // Permitir la modificación si es el mismo vehículo o uno nuevo sin asignación
        rutaActual.setUsuario(RutaRepartidor.getUsuario());
        rutaActual.setVehiculo(RutaRepartidor.getVehiculo());
        rutaActual.setRuta(RutaRepartidor.getRuta());
        rutaRepartidorRepository.save(rutaActual);
    }

    // public void modificarRutaRepartidor(rutaRepartidor RutaRepartidor) {
    // Optional<rutaRepartidor> asignacionExistenteVehiculo =
    // rutaRepartidorRepository
    // .findByVehiculo(RutaRepartidor.getVehiculo());

    // if (asignacionExistenteVehiculo.isPresent()) {
    // throw new RuntimeException(
    // "El vehiculo ya tiene una asignación existente.Elimine la asignación antes de
    // crear una nueva.");
    // } else {
    // rutaRepartidor rutaRepartidorModificado = rutaRepartidorRepository
    // .findById(RutaRepartidor.getIdRutaRepartidor()).get();
    // rutaRepartidorModificado.setUsuario(RutaRepartidor.getUsuario());

    // rutaRepartidorModificado.setVehiculo(RutaRepartidor.getVehiculo());
    // rutaRepartidorModificado.setRuta(RutaRepartidor.getRuta());
    // rutaRepartidorRepository.save(rutaRepartidorModificado);
    // }

    // }

    // metodo para eliminar una ruta asignada a un repartidor
    public void eliminarRutaRepartidor(int idRutaRepartidor) {
        rutaRepartidorRepository.deleteById(idRutaRepartidor);
    }

    // metodo para listar las rutas asignadas a un repartidor
    public List<rutaRepartidor> listarRutasRepartidor() {
        return rutaRepartidorRepository.findAll();
    }

    // metodo para lista rutas asignadas a un repartidor por su id
    public List<rutaRepartidor> buscarRutaRepartidorId(int idUsuario) {
        return rutaRepartidorRepository.findByUsuarioId(idUsuario);
    }

}
