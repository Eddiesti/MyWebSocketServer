package ru.otus.hibernate.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import ru.otus.hibernate.front.FrontendService;

import java.io.IOException;

@WebSocket
public class UserWebSocket {
    private Session session;
    private FrontendService frontendService;
    private String id;

    public UserWebSocket(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        setSession(session);
        id = frontendService.addClient(this);
    }

    @OnWebSocketMessage
    public void onMessage(String name) throws IOException {
        frontendService.sendAddUser(name);
        frontendService.notifyAllUsers(name);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        frontendService.removeClient(id);
    }
}
