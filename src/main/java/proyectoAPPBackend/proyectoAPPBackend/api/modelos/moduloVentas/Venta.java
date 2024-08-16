package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloVentas;

import java.time.LocalDate;

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
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int idVenta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idRepartidor")
    private Usuario repartidor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente")
    private Usuario cliente;

    private LocalDate fechaVenta;

    private double totalVenta;
}
