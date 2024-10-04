package proyectoAPPBackend.proyectoAPPBackend.api.ModelosDTO.modeloDTODinero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListaCargaRepartidorDTO {

    private int idCarga;
    private String nombreRepartidor;
    private String productosCantidad;
    
}
