package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLote;
    private Long idProducto;
    private LocalDate fechaProduccion;
    private Integer cantidadProduccion;
    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean estado = true;

    
}
