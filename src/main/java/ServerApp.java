import freemarker.template.Configuration;
import liked.LikedController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.LikedServlet;
import servlets.LoginServlet;
import servlets.StaticContentServlet;
import servlets.UsersServlet;
import servlets.forms.LoginFormServlet;
import user.UserController;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

//http://localhost:8030/users
public class ServerApp {
    private final static String URL = "jdbc:postgresql://mel.db.elephantsql.com/envmsosd";
    private final static String USER = "envmsosd";
    private final static String PASSWORD = "JeZ9CaaGoPLCzRwXlhD5YTzqoR1fm5W9";

    public static void main(String[] args) throws Exception{
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDirectoryForTemplateLoading(new File("dynamic"));
        conf.setDefaultEncoding("UTF-8");
        Server server = new Server(8030);

        UserController userController = new UserController(conn);
        LikedController likedController = new LikedController();
        LoginFormServlet loginForm = new LoginFormServlet(userController);

        UsersServlet usersPage = new UsersServlet(userController, likedController);
        LikedServlet likedPage = new LikedServlet(userController, likedController);

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new StaticContentServlet("static")), "/static/*");
        handler.addServlet(LoginServlet.class, "/login");
        handler.addServlet(new ServletHolder(loginForm), "/login-form");
        handler.addServlet(new ServletHolder(usersPage), "/users");
        handler.addServlet(new ServletHolder(likedPage), "/liked");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
