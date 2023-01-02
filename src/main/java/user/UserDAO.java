package user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface UserDAO {
    ArrayList<User> getAllUsers();
    User getUserById(int id);
    Optional<User> getById(int id);
    Optional<User> getByEmail(String email);
    void addMessage(int idFrom, int idTo, String message);
    void add(String email, String name, String password, String cookieId) throws SQLException;
}
