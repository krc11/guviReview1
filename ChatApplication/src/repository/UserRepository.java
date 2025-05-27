package repository;

import model.User;
import java.io.*;
import java.util.*;

public class UserRepository {
    private static final String USER_FILE = "data/users.txt";

    public void saveUser(User user) throws IOException {
        FileWriter fw = new FileWriter(USER_FILE, true);
        fw.write(user.getUsername() + "," + user.getEmail() + "\n");
        fw.close();
    }

    public List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(USER_FILE));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                users.add(new User());
            }
        }
        br.close();
        return users;
    }
}