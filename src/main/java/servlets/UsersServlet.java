package servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import liked.Like;
import liked.LikedController;
import user.User;
import user.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UsersServlet extends HttpServlet {
    private final UserController userController;
    private final LikedController likedController;
    Configuration conf = new Configuration();
    int id = 1;
    Map<String, User> data = new HashMap<>();

    public UsersServlet(UserController userController, LikedController likedController) {
        this.userController = userController;
        this.likedController = likedController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter w = resp.getWriter()) {
            // замінити getUserById на getById (повертає Optional)
            data.put("user", userController.getUserById(0));
            conf.getTemplate("dynamic/like-page.ftl").process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter w = response.getWriter()) {
            if (id != 8) {
                // замінити getUserById на getById (повертає Optional)
                data.put("user", userController.getUserById(id));
                conf.getTemplate("dynamic/like-page.ftl").process(data, w);
                String like = request.getParameter("submit2");
                if (like != null) {
                    Cookie cookie = Optional.ofNullable(request.getCookies())
                            .flatMap(c -> Arrays.stream(c).filter(ck -> ck.getName().equals("id")).findFirst()).get();
                    likedController.saveLike(new Like(userController.getByCookie(cookie).getId(), id));
                }
                id++;
            } else {
                response.sendRedirect("/liked");
            }
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}

