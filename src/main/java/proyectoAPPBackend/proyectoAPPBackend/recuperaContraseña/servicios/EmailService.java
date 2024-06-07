package proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.servicios;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.clases.EmailValues;
import org.thymeleaf.context.Context;


@Service
public class EmailService {

    //Clase para enviar coorreos que nos proporciona java
    @Autowired
    JavaMailSender javaMailSender;

    //Motor de plantillas
    @Autowired
    TemplateEngine templateEngine;
    
    @Value("${mail.urlFront}")
    private String urlFront;

    //enviar correo con texto plano
    public void  enviarEmail(){


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("guapurasantaana@gmail.com");
            message.setTo("josechavez4710@gmail.com");
            message.setSubject("Prueba de correo");
            message.setText("Hola, este es un correo de prueba");
    
            javaMailSender.send(message);

    }
    
     //metodo para enviar el correo con la plantilla html para recuperar contraseña
    public void enviarEmailTemplate(EmailValues emailValues){
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();
            model.put("nombreUsuario", emailValues.getNombreUsuario());
            model.put("url", urlFront + emailValues.getJwt());
            context.setVariables(model);


            String  html = templateEngine.process("email-template", context);
            helper.setFrom(emailValues.getEmailFrom());
            helper.setTo(emailValues.getEmailTo());
            helper.setSubject(emailValues.getSubject());
            helper.setText(html, true);
             
            javaMailSender.send(message);
        }catch(MessagingException e){
            e.printStackTrace();
        }

    }



}
