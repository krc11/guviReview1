package main;

import server.ChatServer;
import client.ChatClient;
import ui.ConsoleUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.displayWelcome();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            ui.displayMainMenu();
            int choice = ui.getIntInput("Enter your choice: ", scanner);
            
            switch (choice) {
                case 1:
                    startServer();
                    break;
                case 2:
                    startClient();
                    break;
                case 3:
                    ui.displayGoodbye();
                    System.exit(0);
                    break;
                default:
                    ui.displayError("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void startServer() {
        Thread serverThread = new Thread(() -> {
            ChatServer server = new ChatServer(8080);
            server.start();
        });
        serverThread.start();
    }
    
    private static void startClient() {
        ChatClient client = new ChatClient("localhost", 8080);
        client.start();
    }
}