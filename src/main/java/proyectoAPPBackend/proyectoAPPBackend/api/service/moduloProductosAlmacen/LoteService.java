package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Lote;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.LoteRepository;

@Service
@Transactional
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;
    
    //lista todos los lotes
    public List<Lote> listarLotes() {
        return loteRepository.findAll();
    }

    //guara los lotes
    public void guardarLotes(Lote lote){
        loteRepository.save(lote);
    }

  

    
}
