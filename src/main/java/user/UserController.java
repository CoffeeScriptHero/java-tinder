package user;

import java.util.ArrayList;

public class UserController {
    UserService userService = new UserService();

    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserById(int id) {
        return userService.getUserById(id);
    }
}
