package demo.echo.jsr;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by nathaniel.a.camomot
 */
@ServerEndpoint(value = "/jsrecho")
public class JsrEchoHandler {

	private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void handleOpen(Session session, EndpointConfig config) {
		sessions.add(session);
	}

	// echo
	@OnMessage
	public void handleMessage(Session session, String message) throws IOException {
		for (Session s : sessions) {
			if (s.isOpen()) {
				s.getBasicRemote().sendText(session.getId() + " - " + message);
			}
		}
	}
	
	@OnClose
	public void handleClose(Session session, CloseReason closeReason) {
		sessions.remove(session);
	}

	@OnError
	public void handleError(Session session, Throwable throwable) {
		// error
	}

}
