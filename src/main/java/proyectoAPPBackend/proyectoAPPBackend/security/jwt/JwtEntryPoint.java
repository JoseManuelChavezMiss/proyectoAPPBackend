package proyectoAPPBackend.proyectoAPPBackend.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoAPPBackend.proyectoAPPBackend.Respuestas.Mensaje;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    
    //creamos un logger para mostrar los errores 
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    

    //metodo para manejar la excepcion de autenticacion de usuario es decir si el token es invalido o vacio
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        logger.error("fail en el método commence");
        //res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
        Mensaje mensaje = new Mensaje("token inválido o vacío");
        res.setContentType("application/json");
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.getWriter().write(new ObjectMapper().writeValueAsString(mensaje));
        res.getWriter().flush();
        res.getWriter().close();
    }

}
