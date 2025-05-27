package repository;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static final String USER_FILE = "data/users.txt";

    public static void initializeFiles() {
        try {
            File file = new File(USER_FILE);
            file.getParentFile().mkdirs(); // Ensure /data folder
            if (file.createNewFile()) {
                System.out.println("User file created.");
            }
        } catch (IOException e) {
            System.err.println("Error initializing file: " + e.getMessage());
        }
    }

}
