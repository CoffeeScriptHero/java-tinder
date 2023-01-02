package servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import liked.LikedController;
import user.User;
import user.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class LikedServlet extends HttpServlet {
    private final UserController userController;
    private final LikedController likedController;

    public LikedServlet(UserController userController, LikedController likedController) {
        this.userController = userController;
        this.likedController = likedController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        conf.setDirectoryForTemplateLoading(new File("dynamic"));

        HashMap<String, Object> data = new HashMap<>();

        ArrayList<User> likedUsers = new ArrayList<>();
        while (likedUsers.iterator().hasNext()) {
            likedUsers.add(userController.getUserById(likedController.findUserForId(new User())));
        }// get user "from" by cookie?
        data.put("users", likedUsers);

        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate("people-list.ftl").process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    }
}
