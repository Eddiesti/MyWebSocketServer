package ru.otus.hibernate.websocket;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import ru.otus.hibernate.app.FrontendService;
import ru.otus.hibernate.front.FrontendServiceImpl;

public class UserWebSocketCreator  implements WebSocketCreator {
    private FrontendService frontendService;
    public UserWebSocketCreator(FrontendService frontendService) {
        this.frontendService = frontendService;
    }
    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        UserWebSocket webSocket = new UserWebSocket(this.frontendService);
        return webSocket;
    }
}
