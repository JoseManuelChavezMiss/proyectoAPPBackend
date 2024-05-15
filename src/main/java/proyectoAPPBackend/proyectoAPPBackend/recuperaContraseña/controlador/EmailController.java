package proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.controlador;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.clases.EmailValues;
import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.servicios.EmailService;

@RestController
@RequestMapping("/email")
@CrossOrigin
public class EmailController {

    //inyectamos el servicio de emial
    @Autowired
    EmailService emailService;

    @PostMapping("/enviarEmail")
    public ResponseEntity<?> enviarEmailHtml(@RequestBody EmailValues emailValues){
            emailValues.setEmailFrom("aguapurasantaana@gmail.com");
            emailValues.setSubject("Recuperación contraseña");
            UUID uuid = UUID.randomUUID();
            emailValues.setJwt(uuid.toString());
            emailService.enviarEmailTemplate(emailValues);
            // System.out.println("Correo enviado"+emailService);
            return ResponseEntity.ok().body("Te hemos enviado un correo con las instrucciones para recuperar tu contraseña");

       
    }
}
