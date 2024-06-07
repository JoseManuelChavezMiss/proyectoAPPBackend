package proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    // Configuración de JavaMailSender para enviar correos
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(cleanEmail("aguapurasantaana@gmail.com"));
        mailSender.setPassword("clbonndbbjttigsb");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.localhost", "localhost");

        return mailSender;
    }
    
    // Elimina caracteres no ASCII
    private String cleanEmail(String email) {
        return email.replaceAll("[^\\x00-\\x7F]", ""); 
    }
}