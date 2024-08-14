package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion;

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
public class CargarCamion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCargarCamion;
    
    private Date fecha;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    private String observaciones;
    
    @OneToMany(mappedBy = "cargarCamion", cascade = CascadeType.ALL)  // Cambiar "detalleCarga" a "cargarCamion"
    private List<DetalleCarga> detalles;
    
    // private String estado;
}