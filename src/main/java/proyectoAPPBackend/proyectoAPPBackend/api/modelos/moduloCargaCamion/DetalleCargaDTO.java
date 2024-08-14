package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCargaDTO {

    private int idUsuario;
    private int idProducto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/M/yyyy, HH:mm:ss")
    private Date fecha;
    private String observaciones;
    private int cantidad;
    
}
