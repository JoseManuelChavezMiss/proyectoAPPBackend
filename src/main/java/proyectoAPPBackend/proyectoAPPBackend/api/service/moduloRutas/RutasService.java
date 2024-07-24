package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloRutas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.Ruta;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloRutas.RutasRepository;

@Service
@Transactional
public class RutasService {

    @Autowired
    RutasRepository rutasRepository;

    //listar las rutas
    public List<Ruta> listarRutas() {
        return rutasRepository.findAll();
    }

    //guardar ruta
    public void guardarRuta(Ruta ruta) {
        rutasRepository.save(ruta);
    }

    //METODO PARA BUSCAR UNA RUTA POR ID
    public Ruta findById(int idRuta) {
        return rutasRepository.findById(idRuta).orElse(null);
    }

    //METODO PARA ELIMINAR UNA RUTA
    public void eliminarRuta(int idRuta) {
        rutasRepository.deleteById(idRuta);
    }
    
}
