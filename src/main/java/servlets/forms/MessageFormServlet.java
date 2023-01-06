package servlets.forms;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserController;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class MessageFormServlet extends HttpServlet {
    private UserController userController;

    public MessageFormServlet(UserController userController) {
        this.userController = userController;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ris = req.getParameter("receiverId");
        String sis = req.getParameter("senderId");
        String msg = req.getParameter("message");

        try {
            int receiverId = Integer.parseInt(ris);
            int senderId = Integer.parseInt(sis);
            userController.addMessage(senderId, receiverId, msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
