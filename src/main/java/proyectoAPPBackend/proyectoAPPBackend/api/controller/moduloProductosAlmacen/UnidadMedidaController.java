package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloProductosAlmacen;

import java.util.List;

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
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.UnidadMedida;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen.UnidadMedidaService;

@RestController
@RequestMapping("/unidadMedida")
@CrossOrigin( origins = "*")
//@CrossOrigin( origins = "http://aguasanta.store/")
public class UnidadMedidaController {

    @Autowired
    UnidadMedidaService unidadMedidaService;

    //listar unidadmedida
    @GetMapping("/listar")
    public List<UnidadMedida> listarUnidadMedida(){
        return unidadMedidaService.listarUnidadMeida();
    }


    //guardar medidas
    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarUnidadMedida(@RequestBody UnidadMedida unidadMedida){
        try {
            unidadMedidaService.guardarUnidadMedida(unidadMedida);
            return new ResponseEntity<>(new Mensaje("Unidad de medida creado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al guardar " + e), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modificar/{idUnidadMedida}")
    public ResponseEntity<Mensaje> modificarUnidadMedida(@PathVariable int idUnidadMedida, @RequestBody UnidadMedida unidadMedida){
        UnidadMedida unidadMedidaActual =  unidadMedidaService.findById(idUnidadMedida);
        if(unidadMedidaActual == null){
            return new ResponseEntity<>(new Mensaje("Unidad de medida no encontrada"), HttpStatus.NOT_FOUND);
        }else{
            unidadMedidaActual.setNombre(unidadMedida.getNombre());
            unidadMedidaService.guardarUnidadMedida(unidadMedidaActual);
            return new ResponseEntity<>(new Mensaje("Unidad de medida actualizado con éxito"), HttpStatus.OK);
        }
    }

    @DeleteMapping("/eliminar/{idUnidadMedida}")
    public ResponseEntity<Mensaje> eliminarUnidadMedida(@PathVariable int idUnidadMedida){
        try {
            unidadMedidaService.eliminarUnidadMedida(idUnidadMedida);
            return new ResponseEntity<>(new Mensaje("Unidad de medida eliminado con éxito"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("No puedes eliminar esta unidad de medida porque esta asociada a otros productos"), HttpStatus.NOT_FOUND);
        }

    }




    
}
