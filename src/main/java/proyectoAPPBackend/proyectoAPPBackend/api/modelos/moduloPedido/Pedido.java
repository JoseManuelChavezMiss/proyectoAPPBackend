package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;
    private String numeroPedido;
    private Date fechaPedido;
    @JoinColumn(name = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    private BigDecimal total;
    private String estado;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;
    private String telefono;
    private String direccion;
    
    
}
