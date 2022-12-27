import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import servlets.UsersServlet;

//http://localhost:8080/users
public class ServerApp {
    public static void main(String[] args) throws Exception{
        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(UsersServlet.class, "/users");
//        handler.addServlet(LoginServlet.class, "/login");
//        handler.addServlet(LikedServlet.class, "/liked");

        server.setHandler(handler);
        server.start();
        server.join();

    }
}
