package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.UnidadMedida;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.UnidadMedidaRepository;

@Service
@Transactional
public class UnidadMedidaService {

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    public List<UnidadMedida> listarUnidadMeida(){
        return unidadMedidaRepository.findAll();
    }

    public void guardarUnidadMedida(UnidadMedida unidadMedida){
        unidadMedidaRepository.save(unidadMedida);
    }


    public Optional<UnidadMedida> eliminarUnidadMedida(int idUnidadMedida) {
        Optional<UnidadMedida> unidadMedida = unidadMedidaRepository.findByIdUnidadMedida(idUnidadMedida);
        if (unidadMedida.isPresent()) {
            UnidadMedida unidadMedidaDatos = unidadMedida.get();
            boolean estadoActual = unidadMedidaDatos.isEstado(); // Asume que "estado" es un booleano y tiene un getter isEstado
            unidadMedidaDatos.setEstado(!estadoActual); // Alterna el estado
            unidadMedidaRepository.save(unidadMedidaDatos);
            System.out.println(unidadMedida);
            return Optional.of(unidadMedidaDatos);
        } else {
            return Optional.empty();
        }
    }


    public UnidadMedida findById(int idUnidadMedida){
        return unidadMedidaRepository.findById(idUnidadMedida);
    }
    
}
