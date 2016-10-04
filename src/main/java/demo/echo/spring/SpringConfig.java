package demo.echo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by nathaniel.a.camomot
 */
@Configuration
@EnableWebSocket
public class SpringConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(springEchoHandler(), "/springecho")
                .withSockJS(); //activates SockJS support, remove this and you need use plain WebSocket client
    }

    @Bean
    public WebSocketHandler springEchoHandler() {
        return new SpringEchoHandler();
    }

    @Bean
    public EchoService springEchoService() {
        return new EchoService();
    }

}
