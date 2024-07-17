package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.ProductoRepository;

@Service
@Transactional
public class ProductoSerive implements AlmacenamientoService {

    @Autowired
    private ProductoRepository productoRepository;

    private static final String mediaUrl = "http://localhost:8080/foto/foto/"; // La URL base donde están alojadas las
                                                                               // imágenes

    @Value("${media.location}")
    private String ubicacion; // La ubicación local de los archivos de imágenes

    private Path ubicacionRoot;

    @Override
    @PostConstruct
    public void init() {
        try {
            ubicacionRoot = Paths.get(ubicacion);
            Files.createDirectories(ubicacionRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo que nos permite guardar el archivo y retorna la url de la imagen
    @Override
    public String almacenamiento(MultipartFile archivo) {
        try {
            if (archivo.isEmpty()) {
                throw new RuntimeException("El archivo está vacío.");
            } else {
                String nombreOriginal = archivo.getOriginalFilename();

                Path destinoDirectorio = Paths.get(ubicacion);
                Path destinoArchivo = destinoDirectorio.resolve(nombreOriginal);

                if (!Files.exists(destinoDirectorio)) {
                    Files.createDirectories(destinoDirectorio);
                }

                // Verificar si el archivo ya existe antes de copiarlo
                if (!Files.exists(destinoArchivo)) {
                    Files.copy(archivo.getInputStream(), destinoArchivo, StandardCopyOption.REPLACE_EXISTING);
                }

                String url = mediaUrl + nombreOriginal;

                return url;
            }
        } catch (Exception e) {
            System.out.println("ERROR AL GUARDAR: " + e);
        }

        return null;
    }


    // metodo qu enos permite buscar la foto y mostrar la foto
    @Override
    public Resource cargarRecurso(String nombreArchivo) {
        try {
            Path file = ubicacionRoot.resolve(nombreArchivo).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se pudo leer el archivo: " + nombreArchivo);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL del recurso no válida: " + e.getMessage());
        }
    }

    // metodo para guardar las fotos de los prudctos
    public void guardarProducto(Producto producto, MultipartFile archivo) {

        if (archivo != null) {
            String urlArchivo = almacenamiento(archivo);
            producto.setImagenUrl(urlArchivo);
            productoRepository.save(producto);
        } else {
            throw new RuntimeException("Error al guardar el archivo.");
        }
    }

    // lista todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }


    public void modificarProducto(Producto producto, MultipartFile archivo) {
        // Verificar si el producto existe en la base de datos
        Optional<Producto> productoExistenteOpt = productoRepository.findByIdProducto(producto.getIdProducto());
        if (!productoExistenteOpt.isPresent()) {
            throw new RuntimeException("El producto no existe.");
        }
    
        Producto productoExistente = productoExistenteOpt.get();
    
        // Si se proporciona un archivo, actualizar la URL de la imagen
        if (archivo != null && !archivo.isEmpty()) {
            String urlArchivo = almacenamiento(archivo);
            productoExistente.setImagenUrl(urlArchivo);
        }
        // Si no se proporciona un archivo, la URL de la imagen existente se mantiene automáticamente
    
        // Actualizar otros campos del producto
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setUnidadMedida(producto.getUnidadMedida());
        productoExistente.setPrecio(producto.getPrecio());
        // Añadir cualquier otro campo que necesites actualizar
    
        // Guardar el producto actualizado en la base de datos
        productoRepository.save(productoExistente);
    }

    public void eliminarProducto(int idProducto){
        productoRepository.deleteById(idProducto);
    }
    
    

}
