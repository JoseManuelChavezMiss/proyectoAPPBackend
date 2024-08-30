package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido;

import java.util.Date;

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
import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAsignacionPedido;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idRepartidor", nullable = false)
    private Usuario repartidor;
    
    private Date fechaAsignacion;
    
}
