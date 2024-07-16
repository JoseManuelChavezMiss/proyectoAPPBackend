package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloProductosAlmacen;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import org.springframework.core.io.Resource;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen.ProductoSerive;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    ProductoSerive productoService;

    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarAlmacen(@Valid @RequestPart("producto") Producto producto,
            @RequestPart("archivo") MultipartFile archivo) {
        try {
            // Validar el archivo
            if (archivo == null || archivo.isEmpty()) {
                return new ResponseEntity<>(new Mensaje("Debe colocar una imagen"), HttpStatus.BAD_REQUEST);
            }

            // Procesar el producto y el archivo
            productoService.guardarProducto(producto, archivo);
            return new ResponseEntity<>(new Mensaje("Producto creado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al guardar el producto llene todas las casillas"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foto/{nombreArchivo}")
    public ResponseEntity<Resource> obtenerRecurso(@PathVariable String nombreArchivo) throws IOException {
        Resource file = productoService.cargarRecurso(nombreArchivo);
        String contenType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contenType).body(file);
    }

    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

}
