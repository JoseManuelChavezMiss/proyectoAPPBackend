package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloProductosAlmacen;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Almacen;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen.AlmacenService;

@RestController
@RequestMapping("/almacen")
@CrossOrigin( origins = "*")
public class AlmacenController {

    //inyectamos el servicio
    @Autowired
    AlmacenService almacenService;

    //listar almacenes
    @GetMapping("/listar")
    public List<Almacen> listarAlmacenes() {
        return almacenService.listarAlmacenes();
    }

    //guardar almacen
    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarAlmacen(@RequestBody Almacen almacen) {
        almacenService.guardarAlmacen(almacen);
        return new ResponseEntity<>(new Mensaje("Almacen creado correctamente"), HttpStatus.OK);
    }

    //metodo para modificar almacen
    @PutMapping("/modificar/{idAlmacen}")
    public ResponseEntity<Mensaje> modificarAlmacen(@PathVariable int idAlmacen, @RequestBody Almacen almacen) {
        Almacen almacenActual = almacenService.findById(idAlmacen);
        if (almacenActual == null) {
            return new ResponseEntity<>(new Mensaje("Almacen no encontrado"), HttpStatus.NOT_FOUND);
        } else {
            almacenActual.setNombre(almacen.getNombre());
            almacenActual.setCapacidad(almacen.getCapacidad());
            almacenActual.setCantidad(almacen.getCantidad());
            almacenService.guardarAlmacen(almacenActual);
            return new ResponseEntity<>(new Mensaje("Almacen actualizado con éxito"), HttpStatus.OK);
        }
    }

    //metodo para eliminar
    @DeleteMapping("/eliminarAlmacen/{idAlmacen}")
    public ResponseEntity<Mensaje> borrarCategoria(@PathVariable int idAlmacen) {
        Optional<Almacen> almacen = almacenService.eliminarAlmacen(idAlmacen);
        if(almacen.isPresent()){
            return ResponseEntity.ok(new Mensaje("Almacen eliminada correctamente"));
        }else{
            return ResponseEntity.badRequest().body(new Mensaje("Error al eliminar la almacen"));
        }
    }

    
    
}
