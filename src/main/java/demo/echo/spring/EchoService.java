package demo.echo.spring;

/**
 * Created by nathaniel.a.camomot
 */
public class EchoService {

    public String getMessage(String sender, String message) {
        return String.format("%s - %s", sender, message);
    }

}
