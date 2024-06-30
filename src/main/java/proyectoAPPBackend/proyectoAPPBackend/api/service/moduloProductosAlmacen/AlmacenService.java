package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Almacen;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.AlmacenRepository;

@Service
@Transactional
public class AlmacenService {

    @Autowired
    AlmacenRepository almacenRepository;

    //listar almacenes
    public List<Almacen> listarAlmacenes() {
        return almacenRepository.findAll();
    }

    //guardar almacen
    public void guardarAlmacen(Almacen almacen) {
        almacenRepository.save(almacen);
    }

    //eliminar almacen
    public void eliminarAlmacen(int idAlmacen) {
        almacenRepository.deleteById(idAlmacen);
    }
    
}
