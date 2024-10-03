package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloProductosAlmacen;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen.ProductoSerive;

@RestController
@RequestMapping("/producto")
//@CrossOrigin(origins = "*")
@CrossOrigin( origins = "https://aguasanta.store/")
public class ProductoController {

    @Autowired
    ProductoSerive productoService;

    // guarda los productos
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

    // le pasamos el nombre del archivo y lo carga
    @GetMapping("/foto/{nombreArchivo}")
    public ResponseEntity<Resource> obtenerRecurso(@PathVariable String nombreArchivo) throws IOException {
        Resource file = productoService.cargarRecurso(nombreArchivo);
        String contenType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contenType).body(file);
    }

    // lista todos los productos
    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // meto para listar productos por disponibilidad
    @GetMapping("/listarDisponibles")
    public List<Producto> listarProductosDisponibles() {
        return productoService.listarProductosConDisponibilidad();
    }

    @PostMapping("/modificar")
    public ResponseEntity<Mensaje> modificarProducto(
            @RequestPart("producto") String productoJson,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo) {

        try {
            // Convertir el JSON del producto a un objeto Producto
            ObjectMapper objectMapper = new ObjectMapper();
            Producto producto = objectMapper.readValue(productoJson, Producto.class);

            // Llamar al servicio para modificar el producto
            productoService.modificarProducto(producto, archivo);

            Mensaje mensajeExito = new Mensaje("Producto modificado exitosamente.");
            return ResponseEntity.ok(mensajeExito);
        } catch (Exception e) {
            Mensaje mensajeError = new Mensaje("Error al modificar el producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<Mensaje> eliminarProducto(@PathVariable int idProducto) {
        try {
            productoService.eliminarProducto(idProducto);
            Mensaje mensajeExito = new Mensaje("Producto eliminado exitosamente.");
            return ResponseEntity.ok(mensajeExito);
        } catch (DataIntegrityViolationException e) {
            // Captura de la excepción de violación de integridad de datos (clave foránea)
            Mensaje mensajeError = new Mensaje(
                    "No se puede eliminar el producto porque está asociado a otro registro.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensajeError);
        } catch (Exception e) {
            Mensaje mensajeError = new Mensaje("Error al eliminar el producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }
    
    //actualiza el estado de retornable del producto
    @PutMapping("estadoRetornable/{id}/{retornable}")
    public Optional<Producto> actualizarRetornable(@PathVariable int id, @PathVariable boolean retornable) throws Exception {
        return productoService.actualizarRetornable(id, retornable);
    }
    

}
