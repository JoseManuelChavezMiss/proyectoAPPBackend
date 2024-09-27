package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloCargaCamion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOCargarCamion.ObtenerResumenCargaDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.CargarCamion;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.DetalleCarga;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.DetalleCargaDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas.rutaRepartidor;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloCargaCamion.CargarCamionRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloCargaCamion.DetalleCargaRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.AlmacenRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.ProductoRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloRutas.RutaRepartidorRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloRutas.RutaRepartidorService;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.UsuarioRepository;

@Service
@Transactional
public class CargarCamionService {

    // injecciones de los repositorios necesarios
    @Autowired
    CargarCamionRepository cargaCamionRepository;

    @Autowired
    DetalleCargaRepository detalleCargaRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RutaRepartidorService rutaRepartidorService;

    @Autowired
    RutaRepartidorRepository rutaRepartidorRepository;

    @Autowired
    DetalleCargaService detalleCargaService;

    @Autowired
    AlmacenRepository almacenRepository;

    // metodo para encontrar el usuario por su id
    public Usuario buscarUsuarioPorId(int idUsuario) {
        // si el usuario no existe lanzamos una excepcion
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado");
        } else {
            return usuarioRepository.findById(idUsuario).get();
        }
    }

    // metodo para calcular el total de la carga
    public double calcularTotalCarga(List<DetalleCargaDTO> detalleCarga) {
        int totalCarga = 0;
        for (DetalleCargaDTO carga : detalleCarga) {
            totalCarga += carga.getCantidad();
        }
        return totalCarga;
    }

    // metodo para verificar si el total de la carga sobrepasa la capacidad del
    // vehiculo
    public boolean verificarCapacidadVehiculo(int idUsuario, int totalCarga) {
        List<rutaRepartidor> rutas = buscarRutaRepartidorId(idUsuario);
        System.out.println(rutas);

        for (rutaRepartidor ruta : rutas) {
            if (ruta.getVehiculo().getCapacidadAlmacenaje() < totalCarga) {
                return false; // si el total de la carga sobrepasa la capacidad del vehiculo, retornar false
            }
        }
        return true; // si el total de la carga no sobrepasa la capacidad del vehiculo, retornar true
    }

    // metodo para guardar la carga de un camion
    public CargarCamion guardarCargaCamion(List<DetalleCargaDTO> detalleCarga) {
        if (cargaCamionRepository.verificarCargaCreadaRepartidor(detalleCarga.get(0).getIdUsuario())) {
            throw new RuntimeException("Ya tiene una carga creada");
        } else {
            CargarCamion cargarCamion = new CargarCamion();

            cargarCamion.setFecha(new Date());
            cargarCamion.setUsuario(buscarUsuarioPorId(detalleCarga.get(0).getIdUsuario()));
            cargarCamion.setObservaciones(detalleCarga.get(0).getObservaciones());
            cargarCamion.setCompletado(false);

            // generar y asignar los detalles de la carga
            List<DetalleCarga> detalles = generarDetalleCarga(cargarCamion, detalleCarga);
            cargarCamion.setDetalles(detalles);

            // calcular el total de la carga
            double totalCarga = calcularTotalCarga(detalleCarga);
            // verificar si el total de la carga sobrepasa la capacidad del vehiculo
            if (verificarCapacidadVehiculo(detalleCarga.get(0).getIdUsuario(), (int) totalCarga)) {
                return cargaCamionRepository.save(cargarCamion);
            } else {

                throw new RuntimeException("La carga sobrepasa la capacidad del vehiculo");
            }
        }

    }

    // metodo para generar el detalle de la carga
    // metodo para generar el detalle de la carga
    public List<DetalleCarga> generarDetalleCarga(CargarCamion cargarCamion, List<DetalleCargaDTO> listaDetalleCarga) {
        List<DetalleCarga> detalleCarga = new ArrayList<>();
        Map<Long, Integer> totalPorProducto = new HashMap<>(); // Almacena la suma de cantidades por producto
        List<String> productosSinExistencias = new ArrayList<>();

        // Sumar las cantidades por cada producto en la lista
        for (DetalleCargaDTO detalleDTO : listaDetalleCarga) {
            Long idProducto = (long) detalleDTO.getIdProducto(); // Asegurarse de que sea Long
            totalPorProducto.put(idProducto, totalPorProducto.getOrDefault(idProducto, 0) + detalleDTO.getCantidad());
        }

        // Verificar si la cantidad total para cada producto excede la cantidad
        // disponible en el almacén
        for (DetalleCargaDTO detalleDTO : listaDetalleCarga) {
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            Long idProducto = (long) detalleDTO.getIdProducto(); // Asegurarse de que sea Long
            Integer cantidadTotalSolicitada = totalPorProducto.get(idProducto);

            // Convertir Long e Integer a int antes de llamar al método
            int idProductoInt = idProducto.intValue();
            int cantidadTotalSolicitadaInt = cantidadTotalSolicitada;

            // Verificar la cantidad disponible en el almacén
            if (cargaCamionRepository.verificarCantidadDisponibleAlmacen(idProductoInt, cantidadTotalSolicitadaInt)) {
                DetalleCarga detalle = new DetalleCarga();
                detalle.setCargarCamion(cargarCamion);
                detalle.setProducto(producto);
                detalle.setCantidadLLenos(detalleDTO.getCantidad()); // Aquí solo se añade la cantidad específica
                detalle.setCantidadVacios(0);
                detalleCarga.add(detalle);
            } else {
                productosSinExistencias.add(producto.getNombre());
            }
        }

        // Si hay productos sin existencias, lanzar una excepción con la lista de
        // nombres
        if (!productosSinExistencias.isEmpty()) {
            throw new RuntimeException(
                    "No hay existencias suficientes para los siguientes productos: "
                            + String.join(", ", productosSinExistencias));
        }

        return detalleCarga;
    }

    // public List<DetalleCarga> generarDetalleCarga(CargarCamion cargarCamion,
    // List<DetalleCargaDTO> listaDetalleCarga) {
    // List<DetalleCarga> detalleCarga = new ArrayList<>();
    // List<String> productosSinExistencias = new ArrayList<>();

    // for (DetalleCargaDTO detalleDTO : listaDetalleCarga) {
    // // Obtener el producto por ID en cada iteración
    // Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
    // .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    // // Verificar si hay existencias suficientes
    // if
    // (cargaCamionRepository.verificarCantidadDisponibleAlmacen(detalleDTO.getIdProducto(),
    // detalleDTO.getCantidad())) {
    // DetalleCarga detalle = new DetalleCarga();
    // detalle.setCargarCamion(cargarCamion);
    // detalle.setProducto(producto);
    // detalle.setCantidadLLenos(detalleDTO.getCantidad());
    // detalle.setCantidadVacios(0);
    // detalleCarga.add(detalle);
    // } else {
    // // Agregar el nombre del producto a la lista de productos sin existencias
    // productosSinExistencias.add(producto.getNombre());
    // }
    // }

    // // Si hay productos sin existencias, lanzar una excepción con la lista de
    // nombres
    // if (!productosSinExistencias.isEmpty()) {
    // throw new RuntimeException(
    // "No hay existencias suficientes para los siguientes productos: " +
    // String.join(", ", productosSinExistencias));
    // }

    // return detalleCarga;
    // }

    // public List<DetalleCarga> generarDetalleCarga(CargarCamion cargarCamion,
    // List<DetalleCargaDTO> listaDetalleCarga) {
    // List<DetalleCarga> detalleCarga = new ArrayList<>();

    // Producto verificarProducto =
    // productoRepository.findById(listaDetalleCarga.get(0).getIdProducto())
    // .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    // for (DetalleCargaDTO detalleDTO : listaDetalleCarga) {
    // if(cargaCamionRepository.verificarCantidadDisponibleAlmacen(detalleDTO.getIdProducto(),
    // detalleDTO.getCantidad())){

    // DetalleCarga detalle = new DetalleCarga();

    // detalle.setCargarCamion(cargarCamion);

    // detalle.setProducto(productoRepository.findById(detalleDTO.getIdProducto())
    // .orElseThrow(() -> new RuntimeException("Producto no encontrado")));

    // detalle.setCantidadLLenos(detalleDTO.getCantidad());
    // detalle.setCantidadVacios(0);

    // detalleCarga.add(detalle);
    // }
    // else {
    // throw new RuntimeException("No hay existencias suficientes del producto: " +
    // verificarProducto.getNombre());
    // }

    // }

    // return detalleCarga;
    // }

    // metodo para lista rutas asignadas a un repartidor por su id
    public List<rutaRepartidor> buscarRutaRepartidorId(int idUsuario) {
        List<rutaRepartidor> rutas = rutaRepartidorRepository.findByUsuarioId(idUsuario);

        if (rutas == null || rutas.isEmpty()) {
            // Por ejemplo, lanzar una excepción, retornar una lista vacía o un mensaje de
            // error.
            throw new RuntimeException("No hay rutas asignadas para el usuario con ID " + idUsuario);
        }

        return rutas;
    }

    // metodo para obtener el detalle de la carga a partir de id_cargar_camion
    public void descargarCamion(int idUsuario) {
        cargaCamionRepository.descargarCamion(idUsuario);
    }

    // meto para verificar si ya tiene una carga creada
    // public boolean verificarCargaCreada(int idUsuario) {
    // return cargaCamionRepository.verificarCargaCreada(idUsuario);
    // }

    public List<ObtenerResumenCargaDTO> obtenerResumenCarga(int idUsuario) {
        List<Object[]> resultados = cargaCamionRepository.obtenerResumenCarga(idUsuario);
        List<ObtenerResumenCargaDTO> resumenCarga = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ObtenerResumenCargaDTO dto = new ObtenerResumenCargaDTO();
            dto.setNombreProducto((String) resultado[0]);
            dto.setCantidadLLenos(((Number) resultado[1]).intValue());
            dto.setCantidadVacios(((Number) resultado[2]).intValue());
            resumenCarga.add(dto);
        }
        return resumenCarga;
    }

}
