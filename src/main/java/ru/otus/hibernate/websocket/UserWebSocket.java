package ru.otus.hibernate.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import ru.otus.hibernate.app.FrontendService;
import ru.otus.hibernate.entity.UserDataSet;
import ru.otus.hibernate.front.FrontendServiceImpl;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@WebSocket
public class UserWebSocket {
    private Session session;
    private FrontendService frontendService;
    private Integer id;

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
    }

    @OnWebSocketClose
    public void onClose() {
        frontendService.removeClient(id);
    }
}
