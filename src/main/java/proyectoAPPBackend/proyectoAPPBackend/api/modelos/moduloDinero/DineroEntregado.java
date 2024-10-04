package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloDinero;

import java.time.LocalDateTime;

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
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion.CargarCamion;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DineroEntregado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDineroEntregado;

    @ManyToOne
    @JoinColumn(name = "idCargarCamion", nullable = false)
    private CargarCamion cargarCamion;

    private double montoEntregado;

    private LocalDateTime fechaEntrega;

    private boolean entregado = false;
    
}
