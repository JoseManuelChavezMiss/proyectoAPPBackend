package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloRutas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int idVehiculo;
    private String nombre;
    private String marca;
    private String modelo;
    @Min(value = 0, message = "El valor no puede ser negativo")
    private int  capacidadAlmacenaje;
    private String placa;
    

    
}
