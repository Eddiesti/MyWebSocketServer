package ru.otus.hibernate.launch;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.hibernate.app.DBService;
import ru.otus.hibernate.app.FrontendService;
import ru.otus.hibernate.app.MessageSystemContext;
import ru.otus.hibernate.front.FrontendServiceImpl;
import ru.otus.hibernate.messageSystem.Address;
import ru.otus.hibernate.messageSystem.MessageSystem;
import ru.otus.hibernate.service.DBServiceHibernateImpl;

import ru.otus.hibernate.websocket.UserWebSocketServlet;


public class WebLauncher {
    private final static int PORT = 8060;
    private final static String PUBLIC_HTML = "html_resourses";
    private final static String NAME_DB_SERVICE = "dbService";
    private final static String NAME_FRONT_SERVICE = "frontService";

    public static void launch() throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);
        MessageSystem messageSystem = new MessageSystem();
        MessageSystemContext messageSystemContext = new MessageSystemContext(messageSystem);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        DBService dbService = new DBServiceHibernateImpl(new Address(NAME_DB_SERVICE), messageSystemContext);
        FrontendServiceImpl frontendService = new FrontendServiceImpl(new Address(NAME_FRONT_SERVICE), messageSystemContext);
        messageSystemContext.setDbAddress(dbService.getAddress());
        messageSystemContext.setFrontAddress(frontendService.getAddress());

        context.addServlet(new ServletHolder(new UserWebSocketServlet(frontendService)),
                "/dbwebsocket");
        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
