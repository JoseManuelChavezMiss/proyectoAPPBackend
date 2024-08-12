package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Producto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalleCarga;
    @JoinColumn(name = "idCargarCamion")
    @ManyToOne(optional = false)
    private CargarCamion cargarCamion;
    @JoinColumn(name = "idProducto")
    @ManyToOne(optional = false)
    private Producto producto;
    private int cantidadLLenos;
    private int cantidadVacios;

    
}
