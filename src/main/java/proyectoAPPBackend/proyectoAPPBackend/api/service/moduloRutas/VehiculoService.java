package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloRutas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.Vehiculo;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloRutas.VehiculoRepository;

@Service
@Transactional
public class VehiculoService {

    @Autowired
    VehiculoRepository vehiculoRepository;

    //listar los vehiculos
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    //guardar vehiculo
    public void guardarVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
    }

    //metodo para eliminar un vehiculo
    public void eliminarVehiculo(int idVehiculo) {
        vehiculoRepository.deleteById(idVehiculo);
    }

    //metodo para modificar un vehiculo  utilizamos los set para modificar los datos y guardamos el vehiculo
    public void modificarVehiculo(Vehiculo vehiculo) {
        Vehiculo vehiculoModificado = vehiculoRepository.findById(vehiculo.getIdVehiculo()).get();
        vehiculoModificado.setNombre(vehiculo.getNombre());
        vehiculoModificado.setMarca(vehiculo.getMarca());
        vehiculoModificado.setPlaca(vehiculo.getMarca());
        vehiculoModificado.setModelo(vehiculo.getModelo());
        vehiculoModificado.setCapacidadAlmacenaje(vehiculo.getCapacidadAlmacenaje());
        vehiculoModificado.setPlaca(vehiculo.getPlaca());
        vehiculoRepository.save(vehiculoModificado);
    }
    
}
