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
    // public Optional<Almacen> eliminarAlmacen(int idAlmacen) {
    //     Optional<Almacen> almacen = almacenRepository.findByIdAlmacen(idAlmacen);
    //     if (almacen.isPresent()) {
    //         Almacen almacenDatos = almacen.get();
    //         boolean estadoActual = almacenDatos.isEstado(); // Asume que "estado" es un booleano y tiene un getter isEstado
    //         almacenDatos.setEstado(!estadoActual); // Alterna el estado
    //         almacenRepository.save(almacenDatos);
    //         System.out.println(almacen);
    //         return Optional.of(almacenDatos);
    //     } else {
    //         return Optional.empty();
    //     }
    // }
    
    

    //buscar por id
    public Almacen findById(int idAlmacen) {
        return almacenRepository.findById(idAlmacen);
    }

 
    
}
