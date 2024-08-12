package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion;

import java.math.BigDecimal;

import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.UnidadMedida;


public class DetalleCargaDTO {

    private int idProducto;
    private String nombre;
    private UnidadMedida unidadMedida;
    private BigDecimal precio;
    private String imagenUrl;
    private int cantidad;
    
}
