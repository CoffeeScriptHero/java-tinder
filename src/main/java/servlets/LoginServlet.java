package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Додати  Куки через фільтр
        List<String> strings = Files.readAllLines(Paths.get("static/login.html"));
        try (PrintWriter w = resp.getWriter()) {
            strings.forEach(w::println);
        }
    }
}
