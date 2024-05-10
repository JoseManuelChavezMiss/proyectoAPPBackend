package proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.clases.EmailValues;
import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.servicios.EmailService;

@RestController
public class EmailController {

    //inyectamos el servicio de emial
    @Autowired
    EmailService emailService;
    
    @GetMapping("/enviarEmail")
    public ResponseEntity<?> enviarEmail(){
            emailService.enviarEmail();
            // System.out.println("Correo enviado"+emailService);
            return ResponseEntity.ok().body("Correo enviado");

       
    }

    @PostMapping("/enviarEmail/Html")
    public ResponseEntity<?> enviarEmailHtml(@RequestBody EmailValues emailValues){
            emailService.enviarEmailTemplate(emailValues);
            // System.out.println("Correo enviado"+emailService);
            return ResponseEntity.ok().body("Correo enviado");

       
    }
}
