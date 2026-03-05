import Users.*;
import java.util.ArrayList;
import java.util.List;

public class UserRegistry {
    private List<User> allUsers = new ArrayList<>();

    public void addUser(User user) {
        allUsers.add(user);
    }

    public User findUserById(String id) {
        for (User u : allUsers) {
            if (u.getUserID().equalsIgnoreCase(id)) {
                return u;
            }
        }
        return null; // No user found with that ID
    }
}
