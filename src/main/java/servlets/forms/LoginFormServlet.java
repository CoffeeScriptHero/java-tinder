package servlets.forms;

import user.User;
import user.UserController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class LoginFormServlet extends HttpServlet {
    private UserController userController;

    public LoginFormServlet(UserController userController) {
        this.userController = userController;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Optional<User> ou = userController.getByEmail(email);

        try {
            if (ou.isEmpty()) {
                Cookie cookie = new Cookie("id", UUID.randomUUID().toString());
                userController.add(email, name, password, cookie.getValue());
                resp.addCookie(cookie);
            } else {
                User user = ou.get();
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    resp.addCookie(new Cookie("id", user.getCookieId()));
                } else {
                    resp.setHeader("message", "User already exists!");
                }
            }
        } catch (Exception ex) {
            resp.setHeader("message", "Unexpected error");
            ex.printStackTrace();
        }
    }
}
