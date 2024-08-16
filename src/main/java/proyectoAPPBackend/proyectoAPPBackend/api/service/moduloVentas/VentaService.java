package proyectoAPPBackend.proyectoAPPBackend.api.service.moduloVentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta.VentaCargaCamionDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modelosDTOVenta.VentaListaUsuariosDTO;
import proyectoAPPBackend.proyectoAPPBackend.api.repository.moduloVentas.VentaRepository;

@Service
@Transactional
public class VentaService {

    @Autowired
    VentaRepository ventaRepository;

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
    

}
