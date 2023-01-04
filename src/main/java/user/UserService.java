package user;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final CollectionUserDAO dao;

    public UserService(Connection conn) {
        this.dao = CollectionUserDAO.getInstance(conn);
    }

    public void setMainUser(String cookieId) {
        dao.setMainUser(cookieId);
    }

    public User getMainUser() {
        return dao.getMainUser();
    }

    public ArrayList<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public User getUserById(int id) {
        return dao.getUserById(id);
    }

    public User getByCookie(Cookie cokie) {
        return dao.getByCookie(cokie);
    }


        public Optional<User> getById(int id) {
        return dao.getById(id);
    }

    public Optional<User> getByEmail(String email) {
        return dao.getByEmail(email);
    }

    public void add(String email, String name, String password, String cookieId) throws SQLException {
        dao.add(email, name, password, cookieId);
    }

    public List<Message> getDialogue(int senderId, int receiverId) { return dao.getDialogue(senderId, receiverId); }

    public void addMessage(int senderId, int receiverId, String message) {
        dao.addMessage(senderId, receiverId, message);
    }
}
