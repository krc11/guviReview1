import controller.ChatController;
import repository.FileManager;

public class Main {
    public static void main(String[] args) {
        FileManager.initializeFiles();
        new ChatController().run();
    }
}
