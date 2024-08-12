package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloCargaCamion;

import java.sql.Date;

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
public class CargarCamion {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCargarCamion;
    private Date fecha;
    @JoinColumn(name = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    private String observaciones;
    private String estado;

    
}
