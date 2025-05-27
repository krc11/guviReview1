package controller;

import service.UserService;
import java.util.Scanner;

public class ChatController {
    private UserService userService = new UserService();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n--- Chat Application ---");
            System.out.println("1. Register User");
            System.out.println("2. List Users");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    userService.registerUser(username, email);
                    break;
                case "2":
                    userService.listUsers().forEach(user ->
                        System.out.println(user.getUsername() + " - " + user.getEmail()));
                    break;
                case "0":
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
