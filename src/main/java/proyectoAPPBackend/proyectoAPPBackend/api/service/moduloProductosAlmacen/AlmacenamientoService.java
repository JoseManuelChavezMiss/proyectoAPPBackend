package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource; 


public interface AlmacenamientoService {

    void init();

    String almacenamiento(MultipartFile archivo);

    Resource cargarRecurso(String nombreArchivo);
    
}
