package demo.echo.jsr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by nathaniel.a.camomot
 */
@Configuration
@EnableWebSocket
public class JsrConfig {

    @Bean
    public JsrEchoHandler jsrEchoHandler() {
        return new JsrEchoHandler();
    }

    //Detects JSR 356 beans: ServerEndpointConfig and ServerEndpoint
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
