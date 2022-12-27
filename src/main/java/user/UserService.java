package user;

import java.util.ArrayList;

public class UserService {
    CollectionUserDAO collectionUserDAO = new CollectionUserDAO();

    public ArrayList<User> getAllUsers() {
        return collectionUserDAO.getAllUsers();
    }

    public User getUserById(int id) {
        return collectionUserDAO.getUserById(id);
    }
}
