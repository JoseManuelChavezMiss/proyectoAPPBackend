package proyectoAPPBackend.proyectoAPPBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import proyectoAPPBackend.proyectoAPPBackend.recuperaContraseña.configuracion.MailConfig;

@SpringBootApplication
@Import(MailConfig.class)
public class ProyectoAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoAppBackendApplication.class, args);
	}

}
