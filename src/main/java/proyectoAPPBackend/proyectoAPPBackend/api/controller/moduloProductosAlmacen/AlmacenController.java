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
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloProductosAlmacen.Almacen;
import proyectoAPPBackend.proyectoAPPBackend.api.service.moduloProductosAlmacen.AlmacenService;

@RestController
@RequestMapping("/almacen")
@CrossOrigin( origins = "*")
//@CrossOrigin( origins = "https://aguasanta.store/")
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
    
    
}
