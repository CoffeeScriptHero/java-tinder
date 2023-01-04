package filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
public class CheckCookieFilter implements HttpFilter {

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie[] cs = request.getCookies();
        Optional<Cookie> cookieId = Optional.ofNullable(cs)
                .flatMap(cc -> Arrays.stream(cc).filter(c -> c.getName().equals("id")).findFirst());

        if (cookieId.isPresent()) chain.doFilter(request, response);
        else response.sendRedirect("/login");
    }

}
