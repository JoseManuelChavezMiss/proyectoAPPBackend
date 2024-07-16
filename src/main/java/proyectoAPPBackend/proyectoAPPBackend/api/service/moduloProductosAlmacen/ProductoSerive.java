package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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

   
    private static final String mediaUrl = "http://localhost:8080/foto/foto/"; // La URL base donde están alojadas las imágenes

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

    @Override
    public String almacenamiento(MultipartFile archivo) {
        try {
            if (archivo.isEmpty()) {
                throw new RuntimeException("El archivo está vacío.");
            } else {
                String extension = "";
                String nombreOriginal = archivo.getOriginalFilename();
                if (nombreOriginal != null && nombreOriginal.contains(".")) {
                    extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
                }

                String nombreAleatorio = UUID.randomUUID().toString() + extension;
                Path destinoDirectorio = Paths.get(ubicacion);
                Path destinoArchivo = destinoDirectorio.resolve(nombreAleatorio);

                if (!Files.exists(destinoDirectorio)) {
                    Files.createDirectories(destinoDirectorio);
                }

                Files.copy(archivo.getInputStream(), destinoArchivo, StandardCopyOption.REPLACE_EXISTING);

                String url = mediaUrl + nombreAleatorio;


                return url;
            }
        } catch (Exception e) {
            System.out.println("ERROR AL GUARDAR" + e);
        }

        return null;
    }

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

    public void guardarProducto(Producto producto, MultipartFile archivo) {
          
        if (archivo != null) {
            String urlArchivo = almacenamiento(archivo);
            producto.setImagenUrl(urlArchivo);
            productoRepository.save(producto);
        } else {
            throw new RuntimeException("Error al guardar el archivo.");
        }
    }

    public List<Producto> listarProductos() {
        return  productoRepository.findAll();
    }

    


    
}
    
