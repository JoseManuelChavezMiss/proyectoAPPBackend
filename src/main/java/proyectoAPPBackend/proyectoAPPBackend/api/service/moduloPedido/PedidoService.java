package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloPedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.DetallePedidoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.PedidoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.PedidosPendientesDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.ProductoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOPedidos.productoPedidoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.DetallePedido;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.Pedido;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido.DetallePedidoRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloPedido.PedidoRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.ProductoRepository;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.UsuarioRepository;

@Service
@Transactional
public class PedidoService {

    // injeccion de la interfaz PedidoRepository
    @Autowired
    PedidoRepository pedidoRepository;
    // injeccion de la interfaz usuarioRepository
    @Autowired
    UsuarioRepository usuarioRepository;
    // injeccion de la interfaz DetallePedidoRepository
    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    // injeccion de la interfaz productoRepository
    @Autowired
    ProductoRepository productoRepository;


    public Pedido crearPedido(List<productoPedidoDTO> productos) {

        // creamos un objeto pedido y le asignamos los valores
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(generarNumeroPedido());
        pedido.setFechaPedido(new Date());
        pedido.setUsuario(buscarUsuarioPorId(productos.get(0).getIdUsuario()));
        pedido.setEstado("Pendiente");
        pedido.setTotal(calcularTotalPedido(productos));
        pedido.setTelefono(productos.get(0).getTelefono());
        pedido.setDireccion(productos.get(0).getDireccion());

        // generar y asignar los detalles del pedido
        List<DetallePedido> detalles = generarDetallesPedido(pedido, productos);
        pedido.setDetalles(detalles);

        return pedidoRepository.save(pedido);

    }

    // metodo para encontrar el usuario por su id
    public Usuario buscarUsuarioPorId(int idUsuario) {
        // si el usuairo no existe retornamos usuario no encontrado
        if (!usuarioRepository.existsById(idUsuario)) {
            return null;
        } else {
            // si el usuario existe retornamos el usuario
            return usuarioRepository.findById(idUsuario).get();
        }
    }

    // Este método calcula el total de un pedido.
    public BigDecimal calcularTotalPedido(List<productoPedidoDTO> productoPedido) {
        BigDecimal total = BigDecimal.ZERO; // lo inicializamos en 0

        for (productoPedidoDTO producto : productoPedido) { // recorremos la lista de productos para sumar el total de
                                                            // cada uno
            total = total.add(BigDecimal.valueOf(producto.getTotalProducto()));
        }

        return total;

    }

    // Método para generar los detalles del pedido
    public List<DetallePedido> generarDetallesPedido(Pedido pedido, List<productoPedidoDTO> productos) {
        List<DetallePedido> detallesPedido = new ArrayList<>(); // Lista para almacenar los detalles del pedido

        for (productoPedidoDTO productoPedido : productos) { // Iteramos sobre cada productoPedidoDTO
            DetallePedido detalle = new DetallePedido(); // Creamos un nuevo DetallePedido
            detalle.setPedido(pedido); // Asignamos el pedido al detalle

            // Buscamos el producto por su ID y lo asignamos al detalle
            // Si el producto no se encuentra, lanzamos una excepción
            detalle.setProducto(productoRepository.findById(productoPedido.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado")));

            detalle.setCantidad(productoPedido.getCantidad()); // Asignamos la cantidad del producto al detalle
            detalle.setPrecio(productoPedido.getPrecio()); // Asignamos el precio del producto al detalle

            detallesPedido.add(detalle); // Añadimos el detalle a la lista de detalles del pedido
        }

        return detallesPedido; // Retornamos la lista de detalles del pedido
    }

    // metodo para generar un numero de pedido aleatorio de 8 digitos
    public static String generarNumeroPedido() {
        int length = 8;
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            randomNumber.append(random.nextInt(10));
        }

        return randomNumber.toString();
    }
    

    // metodo para listar los pedidos con el dto pedidoDTO
    public List<PedidoDTO> listarPedidosDTO(int idUsuario) {
        // List<Pedido> pedidos = pedidoRepository.findByUsuarioIdAndEstado(idUsuario, "Asignado");
        List<Pedido> pedidos = pedidoRepository.findByUsuario(buscarUsuarioPorId(idUsuario));
        return pedidos.stream().map(pedido -> {
            PedidoDTO dto = new PedidoDTO();
            dto.setIdPedido(pedido.getIdPedido());
            dto.setNumeroPedido(pedido.getNumeroPedido());
            dto.setFechaPedido(pedido.getFechaPedido().toString());
            dto.setNombreUsuario(pedido.getUsuario().getNombre()); // Solo el nombre del usuario
            dto.setTotal(pedido.getTotal());
            dto.setEstado(pedido.getEstado());
            dto.setTelefono(pedido.getTelefono());
            dto.setDireccion(pedido.getDireccion());
            dto.setDetalles(pedido.getDetalles().stream().map(detalle -> {
                DetallePedidoDTO detalleDTO = new DetallePedidoDTO();
                detalleDTO.setIdDetallePedido(detalle.getIdDetallePedido());
                detalleDTO.setCantidad(detalle.getCantidad());
                detalleDTO.setPrecio(detalle.getPrecio());

                ProductoDTO dtoProducto = new ProductoDTO();
                dtoProducto.setNombre(detalle.getProducto().getNombre());
                dtoProducto.setImagenURL(detalle.getProducto().getImagenUrl());
                dtoProducto.setUnidadMedida(detalle.getProducto().getUnidadMedida().getNombre()); // Asignar la unidad
                                                                                                  // de medida
                dtoProducto.setPrecio(detalle.getProducto().getPrecio());
                detalleDTO.setProductos(Collections.singletonList(dtoProducto));

                return detalleDTO;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    // metodo para listar los pedidos pendientes
    public List<PedidosPendientesDTO> listarPedidosPendientes() {
        List<Object[]> resultados = pedidoRepository.listarPedidosPendientes();
        List<PedidosPendientesDTO> pedidos = new ArrayList<>();
    
        for (Object[] resultado : resultados) {
            PedidosPendientesDTO dto = new PedidosPendientesDTO();
            dto.setIdPedido(((Number) resultado[0]).intValue());
            dto.setCliente((String) resultado[1]);
            dto.setNumeroPedido((String) resultado[2]);
            dto.setDireccion((String) resultado[3]);
            dto.setTelefono((String) resultado[4]);
            dto.setTotalProductos(((Number) resultado[5]).intValue());
            dto.setTotalUnidades(((Number) resultado[6]).intValue());
            pedidos.add(dto);
        }
    
        return pedidos;
    }

    
    
    
}
