package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloVentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta.DetalleVentaDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta.DetalleVentaPedidoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta.VentaCargaCamionDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta.VentaListaUsuariosDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloVentas.DetalleVenta;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloVentas.Venta;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido.PedidoRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.ProductoRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloVentas.VentaRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloCargaCamion.CargarCamionService;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.Pedido;

@Service
@Transactional
public class VentaService {

    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    CargarCamionService cargarCamionService;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    // meto para listar los usuarios por el rol de cliente o por rol generico
    public List<VentaListaUsuariosDTO> listarUsuariosPorRolVenta(int valor) {
        List<Object[]> resultados = ventaRepository.listarUsuariosPorRolVenta(valor);
        List<VentaListaUsuariosDTO> usuarios = new ArrayList<>();

        for (Object[] resultado : resultados) {
            VentaListaUsuariosDTO dto = new VentaListaUsuariosDTO();
            dto.setIdUsuario(((Number) resultado[0]).intValue());
            dto.setEmail((String) resultado[1]);
            dto.setNombre((String) resultado[2]);
            dto.setNombreUsuario((String) resultado[3]);
            dto.setEstado((Boolean) resultado[4]);
            usuarios.add(dto);
        }
        return usuarios;
    }

    // metodo para obtener las cargas de camion del dia por usuario
    public List<VentaCargaCamionDTO> listarCargaCamionRepartidor(int idUsuario) {
        List<Object[]> resultados = ventaRepository.obtenerCargasCamionDelDiaPorUsuario(idUsuario);
        List<VentaCargaCamionDTO> carga = new ArrayList<>();

        for (Object[] resultado : resultados) {
            VentaCargaCamionDTO dto = new VentaCargaCamionDTO();
            dto.setIdCargarCamion(((Number) resultado[0]).intValue());
            dto.setCantidadLLenos(((Number) resultado[1]).intValue());
            dto.setCantidadVacios(((Number) resultado[2]).intValue());
            dto.setIdProducto(((Number) resultado[3]).intValue());
            dto.setNombreProducto((String) resultado[4]);
            dto.setPrecio((BigDecimal) resultado[5]);
            carga.add(dto);
        }
        return carga;
    }

    // Metodo para generar el detalle de la venta
        public List<DetalleVenta> generarDetalleVenta(Venta venta, List<DetalleVentaDTO> listaDetalleVenta) {
        List<DetalleVenta> detalleVenta = new ArrayList<>();
        Map<Long, Integer> totalPorProducto = new HashMap<>(); // Almacena la suma de cantidades por producto
        List<String> productosSinExistencias = new ArrayList<>();
    
        // Sumar las cantidades por cada producto en la lista
        for (DetalleVentaDTO detalleDTO : listaDetalleVenta) {
            Long idProducto = (long) detalleDTO.getIdProducto(); // Convertir int a Long
            totalPorProducto.put(idProducto, totalPorProducto.getOrDefault(idProducto, 0) + detalleDTO.getCantidad());
        }
    
        // Verificar si la cantidad total para cada producto excede la cantidad disponible
        for (DetalleVentaDTO detalleDTO : listaDetalleVenta) {
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto()) // Convertir int a Long
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    
            Long idProducto = (long) detalleDTO.getIdProducto(); // Convertir int a Long
            Integer cantidadTotalSolicitada = totalPorProducto.get(idProducto);
    
            // Convertir Long e Integer a int antes de llamar al método
            int idProductoInt = idProducto.intValue();
            int cantidadTotalSolicitadaInt = cantidadTotalSolicitada;
    
            // Verificar la cantidad disponible con la cantidad total acumulada
            if (ventaRepository.verificarExistenciasRepartidor(detalleDTO.getIdRepartidor(), idProductoInt, cantidadTotalSolicitadaInt)) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setVenta(venta);
                detalle.setProducto(producto);
                detalle.setCantidad(detalleDTO.getCantidad()); // Solo se añade la cantidad específica de este detalle
                detalle.setPrecio(producto.getPrecio());
                detalleVenta.add(detalle);
            } else {
                productosSinExistencias.add(producto.getNombre());
            }
        }
    
        // Si hay productos sin existencias suficientes, lanzar una excepción con la lista de nombres
        if (!productosSinExistencias.isEmpty()) {
            throw new RuntimeException(
                    "No hay existencias suficientes para los siguientes productos: " + String.join(", ", productosSinExistencias));
        }
    
        return detalleVenta;
    }


    // public List<DetalleVenta> generarDetalleVenta(Venta venta, List<DetalleVentaDTO> listaDetalleVenta) {
    //     List<DetalleVenta> detalleVenta = new ArrayList<>();

    //     // Producto verificarProducto =
    //     // productoRepository.findById(listaDetalleVenta.get(0).getIdProducto())
    //     // .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    //     for (DetalleVentaDTO detalleDTO : listaDetalleVenta) {
    //         // Obtener el producto por ID una sola vez
    //         Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
    //                 .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            
    //         if (ventaRepository.verificarExistenciasRepartidor(detalleDTO.getIdRepartidor(), detalleDTO.getIdProducto(),
    //                 detalleDTO.getCantidad())) {
    //             DetalleVenta detalle = new DetalleVenta();
    //             detalle.setVenta(venta);
    //             System.out.println(detalleDTO.getIdProducto());

    //             detalle.setProducto(producto);
    //             detalle.setCantidad(detalleDTO.getCantidad());
    //             detalle.setPrecio(producto.getPrecio());

    //             detalleVenta.add(detalle);
    //         } else {
    //             throw new RuntimeException(
    //                     "No hay existencias suficientes del producto: " + producto.getNombre());
    //         }

    //     }
    //     return detalleVenta;
    // }

    public List<DetalleVenta> generarDetalleVentaPedido(Venta venta, List<DetalleVentaPedidoDTO> listaDetalleVenta) {
        List<DetalleVenta> detalleVenta = new ArrayList<>();
        Map<Long, Integer> totalPorProducto = new HashMap<>(); // Almacena la suma de cantidades por producto
        List<String> productosSinExistencias = new ArrayList<>();
    
        // Sumar las cantidades por cada producto en la lista
        for (DetalleVentaPedidoDTO detalleDTO : listaDetalleVenta) {
            Long idProducto = (long) detalleDTO.getIdProducto(); // Asegurarse de que sea Long
            totalPorProducto.put(idProducto, totalPorProducto.getOrDefault(idProducto, 0) + detalleDTO.getCantidad());
        }
    
        // Verificar si la cantidad total para cada producto excede la cantidad disponible
        for (DetalleVentaPedidoDTO detalleDTO : listaDetalleVenta) {
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    
            Long idProducto = (long) detalleDTO.getIdProducto();
            Integer cantidadTotalSolicitada = totalPorProducto.get(idProducto);

            int idProductoInt = idProducto.intValue();
            int cantidadTotalSolicitadaInt = cantidadTotalSolicitada;
    
            // Verificar las existencias basadas en la cantidad total acumulada
            if (ventaRepository.verificarExistenciasRepartidor(detalleDTO.getIdRepartidor(), idProductoInt, cantidadTotalSolicitadaInt)) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setVenta(venta);
                detalle.setProducto(producto);
                detalle.setCantidad(detalleDTO.getCantidad()); // Solo se añade la cantidad específica de este detalle
                detalle.setPrecio(producto.getPrecio());
    
                detalleVenta.add(detalle);
            } else {
                productosSinExistencias.add(producto.getNombre());
            }
        }
    
        // Si hay productos sin existencias suficientes, lanzar una excepción con la lista de nombres
        if (!productosSinExistencias.isEmpty()) {
            throw new RuntimeException(
                    "No hay existencias suficientes para los siguientes productos: " + String.join(", ", productosSinExistencias));
        }
    
        return detalleVenta;
    }
    

    // public List<DetalleVenta> generarDetalleVentaPedido(Venta venta, List<DetalleVentaPedidoDTO> listaDetalleVenta) {
    //     List<DetalleVenta> detalleVenta = new ArrayList<>();

    //     Producto verificarProducto = productoRepository.findById(listaDetalleVenta.get(0).getIdProducto())
    //             .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    //     for (DetalleVentaPedidoDTO detalleDTO : listaDetalleVenta) {
    //         System.out.println("Producto: " + detalleDTO.getCantidad());

    //         // Obtener el producto por ID para verificar el nombre en caso de error
    //         Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
    //                 .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    //         System.out.println("Nombre: " + producto.getNombre() + ", IdRepartidor: " + detalleDTO.getIdRepartidor()
    //                 + ", IdProducto: " + detalleDTO.getIdProducto() + ", Cantidad: " + detalleDTO.getCantidad());

    //         // Verificar las existencias
    //         if (ventaRepository.verificarExistenciasRepartidor(detalleDTO.getIdRepartidor(), detalleDTO.getIdProducto(),
    //                 detalleDTO.getCantidad())) {
    //             DetalleVenta detalle = new DetalleVenta();
    //             detalle.setVenta(venta);
    //             detalle.setProducto(producto);
    //             detalle.setCantidad(detalleDTO.getCantidad());
    //             detalle.setPrecio(producto.getPrecio());

    //             detalleVenta.add(detalle);
    //         } else {
    //             // Lanzar excepción con el nombre del producto que no tiene existencias
    //             throw new RuntimeException("No hay existencias suficientes del producto: " + producto.getNombre());
    //         }
    //     }

    //     return detalleVenta;
    // }

    // metodo para genera la venta
    public Venta generarVenta(List<DetalleVentaDTO> detalleVenta) {
        Venta venta = new Venta();
        venta.setRepartidor(cargarCamionService.buscarUsuarioPorId(detalleVenta.get(0).getIdRepartidor()));
        venta.setCliente(cargarCamionService.buscarUsuarioPorId(detalleVenta.get(0).getIdCliente()));
        venta.setFechaVenta(detalleVenta.get(0).getFechaVenta());
        venta.setTotalVenta(detalleVenta.get(0).getTotalVenta());
        List<DetalleVenta> detalles = generarDetalleVenta(venta, detalleVenta);
        venta.setDetalles(detalles);
        return ventaRepository.save(venta);

    }

    // metodo para genera la venta pedido
    public Venta generarVentaPedido(List<DetalleVentaPedidoDTO> detalleVenta) {
        Venta venta = new Venta();
        venta.setRepartidor(cargarCamionService.buscarUsuarioPorId(detalleVenta.get(0).getIdRepartidor()));
        venta.setCliente(cargarCamionService.buscarUsuarioPorId(detalleVenta.get(0).getIdCliente()));
        venta.setFechaVenta(detalleVenta.get(0).getFechaVenta());
        venta.setTotalVenta(detalleVenta.get(0).getTotalVenta());
        List<DetalleVenta> detalles = generarDetalleVentaPedido(venta, detalleVenta);
        venta.setDetalles(detalles);
        cambiarEstadoPedidoEntregado(detalleVenta.get(0).getIdPedido());
        return ventaRepository.save(venta);

    }

    // Método para pasar el pedido de pendiente a entregado
    public void cambiarEstadoPedidoEntregado(int idPedido) {
        // Obtener el pedido por id
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Cambiar el estado del pedido a "entregado"
        pedido.setEstado("Entregado");

        // Guardar el pedido actualizado en el repositorio
        pedidoRepository.save(pedido);
    }

}
