package user;

import java.util.ArrayList;

public interface UserDAO {
    ArrayList<User> getAllUsers();

    User getUserById(int id);
}
