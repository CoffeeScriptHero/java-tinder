import freemarker.template.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
//import servlets.LikedServlet;
import servlets.LikedServlet;
import servlets.UsersServlet;

import java.io.File;

//http://localhost:8080/users
public class ServerApp {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDirectoryForTemplateLoading(new File("dynamic"));
        conf.setDefaultEncoding("UTF-8");
        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(UsersServlet.class, "/users");
//        handler.addServlet(LoginServlet.class, "/login");
        handler.addServlet(LikedServlet.class, "/liked");

        server.setHandler(handler);
        server.start();
        server.join();

    }
}
