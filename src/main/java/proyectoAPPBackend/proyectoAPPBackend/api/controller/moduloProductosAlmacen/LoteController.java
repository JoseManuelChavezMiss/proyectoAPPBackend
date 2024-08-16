package proyectoAPPBackend.proyectoAPPBackend.api.controller.moduloProductosAlmacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Lote;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen.LoteService;

@RestController
@RequestMapping("/lote")
@CrossOrigin(origins = "*")
//@CrossOrigin( origins = "http://aguasanta.store/")
public class LoteController {

    @Autowired
    LoteService loteService;

    @GetMapping("/listar")
    public List<Lote> listarAlmacenes() {
        try {
            return loteService.listarLotes();
            
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
            return null;
        }
       
    }

    @PostMapping("/guardar")
    public ResponseEntity<Mensaje> guardarLote(@RequestBody Lote lote){

        try {
            loteService.guardarLotes(lote);
            return new ResponseEntity<>(new Mensaje("Lote creado correctamente"), HttpStatus.OK);
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(new Mensaje("Error " + e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }


    //  @PostMapping("/guardar")
    // public ResponseEntity<Mensaje> guardarAlmacen(@RequestBody Almacen almacen) {
    //     almacenService.guardarAlmacen(almacen);
    //     return new ResponseEntity<>(new Mensaje("Almacen creado correctamente"), HttpStatus.OK);
    // }

}
