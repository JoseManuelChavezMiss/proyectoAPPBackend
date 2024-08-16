package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloProductosAlmacen;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen.ProductoSerive;

@RestController
@RequestMapping("/foto")
// @CrossOrigin(origins = "*")
@CrossOrigin( origins = "http://aguasanta.store/")
public class FotosProductoController {

    @Autowired
    ProductoSerive productoService;


    @GetMapping("/foto/{nombreArchivo}")
    public ResponseEntity<Resource> obtenerRecurso(@PathVariable String nombreArchivo) throws IOException {
        Resource file = productoService.cargarRecurso(nombreArchivo);
        String contenType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contenType ).body(file);
    }
    
}
