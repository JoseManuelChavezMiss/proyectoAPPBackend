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

    // @PostMapping("/guardar")
    // public ResponseEntity<Mensaje> guardarProducto(@RequestParam("producto")
    // String productoJson,
    // @RequestParam("archivo") MultipartFile multipaFile) {
    // ObjectMapper objectMapper = new ObjectMapper();
    // try {
    // Producto producto = objectMapper.readValue(productoJson, Producto.class);
    // productoService.guardarProducto(producto, multipaFile);
    // return new ResponseEntity<>(new Mensaje("Producto creado correctamente"),
    // HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(new Mensaje("Error al guardar el producto: " +
    // e.getMessage()),
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarAlmacen(@RequestPart("producto") Producto producto,
            @RequestPart("archivo") MultipartFile archivo) {

        try {
            productoService.guardarProducto(producto, archivo);
            return new ResponseEntity<>(new Mensaje("Producto creado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al guardar el producto:||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PostMapping("/guardar")
    // public ResponseEntity<Mensaje> guardarAlmacen(@RequestBody Almacen almacen) {
    // almacenService.guardarAlmacen(almacen);
    // return new ResponseEntity<>(new Mensaje("Almacen creado correctamente"),
    // HttpStatus.OK);
    // }

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
