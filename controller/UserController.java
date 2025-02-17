package controller;

import model.User;
import java.util.Vector;

public class UserController {
    private Vector<User> users;

    public UserController() {
        this.users = new Vector<>();
    }

    /**
     * Loads predefined user data and returns a Vector of users.
     *
     * @return A Vector containing user objects.
     */
    public Vector<User> loadUserData() {
        Vector<User> loadedUsers = new Vector<>();
        loadedUsers.add(new User("mike", "my_passwd", "Mike", "Smith", "07771234567"));
        loadedUsers.add(new User("james.cameron@gmail.com", "angel", "James", "Cameron", "07777654321"));
        loadedUsers.add(new User("julia.roberts@gmail.com", "change_me", "Julia", "Roberts", "07770123456"));

        this.users = loadedUsers; // Assign loaded users to the instance variable
        return this.users;
    }

    /**
     * Prints all users in the system.
     */
    public void printAllUsers() {
        System.out.println("There are: " + users.size() + " users in the system.");
        System.out.println(String.format("%-25s| %-15s| %-15s| %-15s| %-15s",
                "Username", "Password", "First Name", "Last Name", "Mobile Number"));
        System.out.println("-------------------------------------------------------------------------------------------");

        for (User user : users) {
            System.out.println(user);
        }
        System.out.println();
    }
}
