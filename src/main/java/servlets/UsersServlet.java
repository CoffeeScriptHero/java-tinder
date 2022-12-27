package servlets;

import user.Profile;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter w = resp.getWriter()) {
            List<String> strings = Files.readAllLines(Paths.get("dynamic/like-page.ftl"));
            strings.forEach(w::println);
        }
    }

    ArrayList<String> s = new ArrayList<>();
    int i = 0;
    Profile users = new Profile();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter w = response.getWriter()) {

            request.setAttribute("user.name", users.selectUsers().get(i).getName());
            request.setAttribute("user.img", users.selectUsers().get(i).getImg());
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("dynamic/like-page.ftl");
            requestDispatcher.forward(request, response);

            String like = request.getParameter("submit2");
            if(i != 7) {
                if (like != null) {s.add("Yes");}
                else {s.add("No");}
                w.println(s);
                i++;
            } else {
                response.sendRedirect("/liked");
            }
        }
    }
}

