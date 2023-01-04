package filters;

import user.User;
import user.UserController;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class CheckCookieFilter implements HttpFilter {
    private UserController userController;

    public CheckCookieFilter(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie[] cs = request.getCookies();
        Optional<Cookie> cookieId = Optional.ofNullable(cs)
                .flatMap(cc -> Arrays.stream(cc).filter(c -> c.getName().equals("id")).findFirst());

        if (cookieId.isPresent()) {
            // тобто мейн юзера не встановлено, бо перелік id у бд починається з 1
            User user = userController.getMainUser();
            if (user.getId() == 0 || !Objects.equals(user.getCookieId(), cookieId.get().getValue())) {
                userController.setMainUser(cookieId.get().getValue());
            }
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

}
