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
import proyectoAPPBackend.proyectoAPPBackend.api.modelos.moduloPedido.Pedido;
import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.dto.EmailValuesDTO;

import org.thymeleaf.context.Context;

@Service
public class EmailService {

    // Clase para enviar coorreos que nos proporciona java
    @Autowired
    JavaMailSender javaMailSender;

    // Motor de plantillas
    @Autowired
    TemplateEngine templateEngine;

    @Value("${mail.urlFront}")
    private String urlFront;

    // enviar correo con texto plano
    public void enviarEmail() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("guapurasantaana@gmail.com");
        message.setTo("josechavez4710@gmail.com");
        message.setSubject("Prueba de correo");
        message.setText("Hola, este es un correo de prueba");

        javaMailSender.send(message);

    }

    // metodo para enviar el correo con la plantilla html para recuperar contraseña
    public void enviarEmailTemplate(EmailValuesDTO emailValues) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();

            model.put("nombreUsuario", emailValues.getNombreUsuario());
            model.put("url", urlFront + emailValues.getJwt());
            context.setVariables(model);

            String html = templateEngine.process("email-template", context);
            helper.setFrom(emailValues.getEmailFrom());
            helper.setTo(emailValues.getEmailTo());
            helper.setSubject(emailValues.getSubject());
            helper.setText(html, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    // metodo para enviar correo el numero de pedido al cliente
    public void enviarEmailPedido(Pedido pedido) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("nombreUsuario", pedido.getUsuario().getNombre());
            model.put("numeroPedido", pedido.getNumeroPedido());
            model.put("total", pedido.getTotal());
            model.put("fechaPedido", pedido.getFechaPedido());
            context.setVariables(model);

            String html = templateEngine.process("email-pedido", context);
            String from = "guapurasantaana@gmail.com";
            String subject = "Confirmación de Pedido";
            helper.setFrom(from);
            helper.setTo(pedido.getUsuario().getEmail());
            helper.setSubject(subject);
            helper.setText(html, true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
