package user;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    ArrayList<User> getAllUsers();
    User getUserById(int id);
   User getByCookie(Cookie cokie);
    Optional<User> getById(int id);
    Optional<User> getByEmail(String email);
    List<Message> getDialogue(int senderId, int receiverId);
    void addMessage(int senderId, int receiverId, String message);
    void add(String email, String name, String password, String cookieId) throws SQLException;
}
