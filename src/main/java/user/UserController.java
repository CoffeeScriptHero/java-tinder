package user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserController {
    private final UserService service;

    public UserController(Connection conn) {
        this.service = new UserService(conn);
    }

    public ArrayList<User> getAllUsers() {
        return service.getAllUsers();
    }

    public User getUserById(int id) {
        return service.getUserById(id);
    }

    public Optional<User> getById(int id) { return service.getById(id); }

    public Optional<User> getByEmail(String email) { return service.getByEmail(email); }

    public void add(String email, String name, String password, String cookieId) throws SQLException {
        service.add(email, name, password, cookieId);
    }

    public void addMessage(int idFrom, int idTo, String message) {
        service.addMessage(idFrom, idTo, message);
    }
}
