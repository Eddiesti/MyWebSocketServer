package ru.otus.hibernate.websocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import ru.otus.hibernate.app.FrontendService;
import ru.otus.hibernate.front.FrontendServiceImpl;

import java.util.concurrent.TimeUnit;

public class UserWebSocketServlet extends WebSocketServlet  {
    private final static long LOGOUT_TIME = TimeUnit.MINUTES.toMillis(10);
    private FrontendService frontendService;
    public UserWebSocketServlet(FrontendService frontendService) {
        this.frontendService = frontendService;
    }
    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator(new UserWebSocketCreator(frontendService));
    }
}
