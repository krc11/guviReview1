package service;

import model.User;
import repository.UserRepository;
import util.InputValidator;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class UserService {
    private UserRepository userRepo = new UserRepository();

    public boolean registerUser(String username, String email) {
        if (!InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email.");
            return false;
        }

        User user = new User();
        try {
            userRepo.saveUser(user);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    public List<User> listUsers() {
        try {
            return userRepo.getAllUsers();
        } catch (IOException e) {
            System.err.println("Error reading users: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
