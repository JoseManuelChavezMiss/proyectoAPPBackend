package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloDinero;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTODinero.ListaCargaRepartidorDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTODinero.ListaDineroEntregadoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTODinero.ListaDineroRepartidorDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTODinero.NuevoIngresoDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloDinero.DineroEntregado;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloCargaCamion.CargarCamionRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloDinero.DineroEntregadoRepository;
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;
import proyectoAPPBackend.proyectoAPPBackend.security.repository.UsuarioRepository;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.CargarCamion;

@Service
@Transactional
public class DineroEntregadoService {

    @Autowired
    DineroEntregadoRepository dineroEntregadoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CargarCamionRepository cargarCamionRepository;

    // Metodo para guardar el dinero entregado
    public void guardarDineroEntregado(NuevoIngresoDTO dineroEntregado) {
        System.out.println(dineroEntregado.getIdUsuario());
        // Busca el usuario por su ID
        CargarCamion cargarCamion = buscarCargaCamionPorId(dineroEntregado.getIdUsuario());

        // Crea un nuevo objeto DineroEntregado y asigna el repartidor
        DineroEntregado dineroEntregadoDatos = new DineroEntregado();
        dineroEntregadoDatos.setCargarCamion(cargarCamion);
        dineroEntregadoDatos.setMontoEntregado(dineroEntregado.getMontoIngreso());
        dineroEntregadoDatos.setFechaEntrega(LocalDateTime.now());
        dineroEntregadoDatos.setEntregado(false);

        // Guarda el dinero entregado
        dineroEntregadoRepository.save(dineroEntregadoDatos);
    }

    // metodo para encontrar el usuario por su id
    public Usuario buscarUsuarioPorId(int idUsuario) {
        // si el usuario no existe lanzamos una excepcion
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado");
        } else {
            return usuarioRepository.findById(idUsuario).get();
        }
    }

    // //metodo para enlistar el dinero entregado
    // public List<DineroEntregado> listarDineroEntregado() {
    // return dineroEntregadoRepository.findAll();
    // }

    // Listar carga del repartidor
    public List<ListaCargaRepartidorDTO> listarCargaRepartidor() {
        List<Object[]> resultados = dineroEntregadoRepository.listaCargaRepartidor();
        List<ListaCargaRepartidorDTO> listaCargaRepartidor = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ListaCargaRepartidorDTO dto = new ListaCargaRepartidorDTO();
            dto.setIdCarga((int) resultado[0]);
            dto.setNombreRepartidor((String) resultado[1]);
            dto.setProductosCantidad((String) resultado[2]);

            listaCargaRepartidor.add(dto);

        }
        return listaCargaRepartidor;
    }

    // listar dinero entregado
    public List<ListaDineroEntregadoDTO> listarDineroEntregado() {
        List<Object[]> resultados = dineroEntregadoRepository.listaDineroEntregado();
        List<ListaDineroEntregadoDTO> listaDineroEntregado = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ListaDineroEntregadoDTO dto = new ListaDineroEntregadoDTO();
            dto.setIdDineroEntregado((int) resultado[0]);
            dto.setIdUsuario((int) resultado[1]);
            dto.setRepartidor((String) resultado[2]);
            dto.setMontoEntregado((double) resultado[3]);

            // Convertir de Timestamp a LocalDateTime
            Timestamp timestamp = (Timestamp) resultado[4];
            if (timestamp != null) {
                dto.setFechaEntrega(timestamp.toLocalDateTime()); // Cambia a LocalDateTime
            }

            dto.setEntregado((boolean) resultado[5]);

            listaDineroEntregado.add(dto);
        }

        return listaDineroEntregado;
    }

    // metodo para que el repartidor pueda ver el dinero que ha entregado
    public List<ListaDineroRepartidorDTO> obtenerDineroVentasRepartidor(int idUsuario) {
        List<Object[]> resultados = dineroEntregadoRepository.obtenerDineroVentasRepartidor(idUsuario);
        List<ListaDineroRepartidorDTO> listaDineroEntregado = new ArrayList<>();

        for (Object[] resultado : resultados) {
            ListaDineroRepartidorDTO dto = new ListaDineroRepartidorDTO();
            dto.setIdCarga((int) resultado[0]);
            dto.setMontoEntregado(((BigDecimal) resultado[1]).doubleValue());
            dto.setTotalVentas(((BigDecimal) resultado[2]).doubleValue());
            dto.setTotalEntregar(((BigDecimal) resultado[3]).doubleValue());

            listaDineroEntregado.add(dto);
        }

        return listaDineroEntregado;
    }

    // en cargar camion con su repository haremos lo mismo que con usuairo
    public CargarCamion buscarCargaCamionPorId(int idCarga) {
        if (!cargarCamionRepository.existsById(idCarga)) {
            throw new RuntimeException("Carga no encontrada");
        } else {
            return cargarCamionRepository.findById(idCarga).get();
        }
    }

    //metodo para completar la carga y descargar el camion
    public void completarCargaDescargarCamion(int idCarga) {
        dineroEntregadoRepository.completarCargaDescargarCamion(idCarga);
    }

}
