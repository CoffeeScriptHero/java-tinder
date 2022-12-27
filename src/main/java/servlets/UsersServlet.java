package servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import user.User;
import user.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UsersServlet extends HttpServlet {
    Configuration conf = new Configuration();
    ArrayList<String> likedList = new ArrayList<>();
    int id = 1;
    UserController userController = new UserController();
    Map<String, User> data = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter w = resp.getWriter()) {
            data.put("user", userController.getUserById(0));
            conf.getTemplate("java-tinder/dynamic/like-page.ftl").process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter w = response.getWriter()) {
            if (id != 8) {
            data.put("user", userController.getUserById(id));
            conf.getTemplate("java-tinder/dynamic/like-page.ftl").process(data, w);
            String like = request.getParameter("submit2");
                if (like != null) {
                    likedList.add("Yes");
                } else {
                    likedList.add("No");
                }
                w.println(likedList);
                id++;
            } else {
                response.sendRedirect("/liked");
            }
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}

