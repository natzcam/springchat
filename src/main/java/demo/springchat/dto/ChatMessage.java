package demo.springchat.dto;

/**
 * Created by nathaniel.a.camomot on 1/13/2016.
 */
public class ChatMessage {

    private String message;
    private String sender;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
