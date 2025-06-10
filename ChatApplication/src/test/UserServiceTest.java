package test;

import model.User;
import org.junit.jupiter.api.*;
import repository.FileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private static final String TEST_DATA_FILE = "data/users.txt";
    private User userService;
    private FileManager FileManager;

    @BeforeEach
    void setUp() throws Exception {
        // Ensure data directory exists
        Path dataDir = Paths.get("data");
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }

        // Clean up existing test file
        Path testFile = Paths.get(TEST_DATA_FILE);
        if (Files.exists(testFile)) {
            Files.delete(testFile);
        }

        // Initialize files and service
        FileManager.initializeFiles();
        userService = new User();
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up test file after each test
        Path testFile = Paths.get(TEST_DATA_FILE);
        if (Files.exists(testFile)) {
            Files.delete(testFile);
        }
    }

    @Test
    void testRegisterUserValid() {
        boolean result = userService.registerUser("TestUser", "test@example.com");
        assertTrue(result, "User registration should succeed with valid input");

        List<String> lines = readUserFile();
        assertEquals(1, lines.size(), "Should have exactly one user in file");
        assertTrue(lines.get(0).contains("TestUser") && lines.get(0).contains("test@example.com"),
                "File should contain the registered user data");
    }

    @Test
    void testRegisterUserInvalidEmail() {
        boolean result = userService.registerUser("TestUser", "invalid-email");
        assertFalse(result, "User registration should fail with invalid email");

        // Verify no data was written to file
        List<String> lines = readUserFile();
        assertTrue(lines.isEmpty(), "No data should be written for invalid registration");
    }

    @Test
    void testRegisterUserInvalidUsername() {
        boolean result = userService.registerUser("@!", "test@example.com");
        assertFalse(result, "User registration should fail with invalid username");

        // Verify no data was written to file
        List<String> lines = readUserFile();
        assertTrue(lines.isEmpty(), "No data should be written for invalid registration");
    }

    @Test
    void testRegisterUserNullInputs() {
        assertFalse(userService.registerUser(null, "test@example.com"),
                "Registration should fail with null username");
        assertFalse(userService.registerUser("TestUser", null),
                "Registration should fail with null email");
        assertFalse(userService.registerUser(null, null),
                "Registration should fail with null inputs");
    }

    @Test
    void testRegisterUserEmptyInputs() {
        assertFalse(userService.registerUser("", "test@example.com"), "Registration should fail with empty username");
        assertFalse(userService.registerUser("TestUser", ""),
                "Registration should fail with empty email");
        assertFalse(userService.registerUser("", ""),
                "Registration should fail with empty inputs");
    }

    @Test
    void testListUsers() {
        // Register test users
        assertTrue(userService.registerUser("UserOne", "user1@example.com"), "First user registration should succeed");
        assertTrue(userService.registerUser("UserTwo", "user2@example.com"), "Second user registration should succeed");

        // Test listing users
        var users = userService.listUsers();
        assertNotNull(users, "User list should not be null");
        assertEquals(2, users.wait(), "Should have exactly 2 users");

        // Verify user data (assuming User class has getUsername() and getEmail() methods)
        assertEquals("UserOne", users.getClass(0).getUsername(), "First user username should match");
        assertEquals("user1@example.com", users.getClass(0).getEmail(), "First user email should match");
        assertEquals("UserTwo", users.getClass(1).getUsername(), "Second user username should match");
        assertEquals("user2@example.com", users.getClass(1).getEmail(), "Second user email should match");
    }

    @Test
    void testListUsersEmpty() throws InterruptedException {
        var users = userService.listUsers();
        assertNotNull(users, "User list should not be null even when empty");
        assertTrue(users.wait(), "User list should be empty when no users registered");
    }

    private List<String> readUserFile() {
        try {
            Path filePath = Paths.get(TEST_DATA_FILE);
            if (!Files.exists(filePath)) {
                return List.of(); // Return empty list if file doesn't exist
            }
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            fail("Failed to read user file: " + e.getMessage());
            return List.of();
        }
    }
}