package proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen;

import jakarta.persistence.Column;
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
public class Producto {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idProducto;
    private String nombre;
    private int cantidad;
    private float precio;
    private String imagenUrl;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idAlmacen")
    private Almacen almacen;
    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean estado;
    




    
}
