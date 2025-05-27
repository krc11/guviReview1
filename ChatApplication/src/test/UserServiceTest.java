package test;

import org.junit.jupiter.api.*;
import src.service.UserService;
import src.repository.FileManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private static final String TEST_DATA_FILE = "data/users.txt";
    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        File file = new File(TEST_DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
        FileManager.initializeFiles();
        userService = new UserService();
    }

    @Test
    void testRegisterUserValid() {
        boolean result = userService.registerUser("TestUser", "test@example.com");
        assertTrue(result);
        List<String> lines = readUserFile();
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).contains("TestUser,test@example.com"));
    }

    @Test
    void testRegisterUserInvalidEmail() {
        boolean result = userService.registerUser("TestUser", "invalid-email");
        assertFalse(result);
    }

    @Test
    void testRegisterUserInvalidUsername() {
        boolean result = userService.registerUser("@!", "test@example.com");
        assertFalse(result);
    }

    @Test
    void testListUsers() {
        userService.registerUser("UserOne", "user1@example.com");
        userService.registerUser("UserTwo", "user2@example.com");
        var users = userService.listUsers();
        assertEquals(2, users.size());
        assertEquals("UserOne", users.get(0).getUsername());
        assertEquals("user2@example.com", users.get(1).getEmail());
    }

    private List<String> readUserFile() {
        try {
            return Files.readAllLines(Path.of(TEST_DATA_FILE));
        } catch (Exception e) {
            fail("Failed to read user file: " + e.getMessage());
            return List.of();
        }
    }
}
