package servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import liked.Like;
import liked.LikedController;
import user.User;
import user.UserController;

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
    private final int MAX_ID = 8;
    Configuration conf = new Configuration();
    int id = 1;
    Map<String, User> data = new HashMap<>();

    public UsersServlet(UserController userController, LikedController likedController) {
        this.userController = userController;
        this.likedController = likedController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (id >= MAX_ID) {
            id = 1;
        }

        try (PrintWriter w = resp.getWriter()) {
            data.put("user", userController.getUserById(id));
            conf.getTemplate("dynamic/like-page.ftl").process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter w = response.getWriter()) {
            if (id < MAX_ID) {
                String like = request.getParameter("submit2");
                if (like != null) {
                    Cookie cookie = Optional.ofNullable(request.getCookies())
                            .flatMap(c -> Arrays.stream(c).filter(ck -> ck.getName().equals("id")).findFirst()).get();
                    likedController.saveLike(new Like(userController.getByCookie(cookie).getId(), id));
                }
                id++;
                if (id == MAX_ID) {
                    response.sendRedirect("/liked");
                } else {
                    data.put("user", userController.getUserById(id));
                    conf.getTemplate("dynamic/like-page.ftl").process(data, w);
                }
            }
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}

