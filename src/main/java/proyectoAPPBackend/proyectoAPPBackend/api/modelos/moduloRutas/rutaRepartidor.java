package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas;

import proyectoAPPBackend.proyectoAPPBackend.security.entity.Usuario;

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


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class rutaRepartidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRutaRepartidor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idRuta")
    private Ruta ruta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idVehiculo")
    private Vehiculo vehiculo;

}
