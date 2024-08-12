package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloCargaCamion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.CargarCamion;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.DetalleCarga;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.DetalleCargaDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloCargaCamion.CargarCamionRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloCargaCamion.DetalleCargaRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloProductosAlmacen.ProductoRepository;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.UsuarioRepository;

@Service
@Transactional
public class CargarCamionService {

    //injecciones de los repositorios necesarios
    @Autowired
    CargarCamionRepository cargaCamionRepository;

    @Autowired
    DetalleCargaRepository detalleCargaRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    // //metodo para crear la carga del camion
    // public CargarCamion crearCargaCamion(CargarCamion cargarCamion){

    //     Usuario usuario = usuarioRepository.findById(cargarCamion.getUsuario().getId())
    //         .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    //     CargarCamion cargaCamion = new CargarCamion();
    //     cargarCamion.setUsuario(usuario);
    //     cargarCamion.setFecha(cargarCamion.getFecha());
    //     cargarCamion.setObservaciones(cargarCamion.getObservaciones());
    //     cargarCamion.setEstado("Pendiente");
        
    //     cargaCamionRepository.save(cargarCamion);

    //     crearCargaCamion(cargarCamion, )
        
        
    
    // }


    // //metodo para generar el detalle de la carga
    // public void crearDetalleCarga(CargarCamion cargarCamion, List<DetalleCargaDTO> detallesCarga){
    //     for (DetalleCargaDTO detalleCargaDTO : detallesCarga) {

    //         Producto producto = productoRepository.findById(detalleCargaDTO.getIdProducto())
    //             .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    //         DetalleCarga detalleCarga = new DetalleCarga();
    //         detalleCarga.setCargarCamion(cargarCamion);
    //         detalleCarga.setProducto(producto);
    //         detalleCarga.setCantidadLLenos(detalleCargaDTO.getCantidad());
    //         detalleCargaRepository.save(detalleCarga);
    //     }
    // }
    
}
