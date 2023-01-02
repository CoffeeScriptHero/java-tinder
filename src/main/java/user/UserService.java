package user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserService {
    private final CollectionUserDAO dao;

    public UserService(Connection conn) {
        this.dao = CollectionUserDAO.getInstance(conn);
    }

    public ArrayList<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public User getUserById(int id) {
        return dao.getUserById(id);
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

    public void addMessage(int idFrom, int idTo, String message) {
        dao.addMessage(idFrom, idTo, message);
    }
}
