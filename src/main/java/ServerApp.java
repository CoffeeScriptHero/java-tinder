import filters.CheckCookieFilter;
import freemarker.template.Configuration;
import liked.LikedController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import servlets.forms.LoginFormServlet;
import servlets.forms.MessageFormServlet;
import user.UserController;

import javax.servlet.DispatcherType;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.EnumSet;

//http://localhost:8030/users
public class ServerApp {
    private final static String URL = "jdbc:postgresql://mel.db.elephantsql.com/envmsosd";
    private final static String USER = "envmsosd";
    private final static String PASSWORD = "JeZ9CaaGoPLCzRwXlhD5YTzqoR1fm5W9";
    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);
    public static void main(String[] args) throws Exception{
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDirectoryForTemplateLoading(new File("dynamic"));
        conf.setDefaultEncoding("UTF-8");
        Server server = new Server(8030);

        UserController userController = new UserController(conn);
        LikedController likedController = new LikedController();

        LoginFormServlet loginForm = new LoginFormServlet(userController);
        MessageFormServlet messageForm = new MessageFormServlet(userController);

        UsersServlet usersPage = new UsersServlet(userController, likedController);
        LikedServlet likedPage = new LikedServlet(userController, likedController);
        MessagesServlet chatPage = new MessagesServlet(conf, userController);

        CheckCookieFilter cookieFilter = new CheckCookieFilter(userController);

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new StaticContentServlet("static")), "/static/*");
        handler.addServlet(LoginServlet.class, "/login");
        handler.addServlet(new ServletHolder(loginForm), "/login-form");
        handler.addServlet(new ServletHolder(usersPage), "/users");
        handler.addServlet(new ServletHolder(likedPage), "/liked");
        handler.addServlet(new ServletHolder(chatPage), "/messages/*");
        handler.addServlet(new ServletHolder(messageForm), "/message-form");

        handler.addFilter(new FilterHolder(cookieFilter), "/users", ft);
        handler.addFilter(new FilterHolder(cookieFilter), "/liked", ft);
        handler.addFilter(new FilterHolder(cookieFilter), "/messages/*", ft);

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
