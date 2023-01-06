package servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.Message;
import user.User;
import user.UserController;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesServlet extends HttpServlet {
    private Configuration conf;
    private UserController userController;

    public MessagesServlet(Configuration conf, UserController userController) {
        this.conf = conf;
        this.userController = userController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String path = req.getPathInfo();

        if (path.startsWith("/")) {
            id = Integer.parseInt(path.substring(1));
        }

        User mainUser = userController.getMainUser();
        User user = userController.getUserById(id);
        List<Message> messages = userController.getDialogue(mainUser.getId(), id);
        Map<String, Object> data = new HashMap<>();

        data.put("user", user);
        data.put("mainUser", mainUser);
        data.put("messages", messages);

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("chat.ftl").process(data, w);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
