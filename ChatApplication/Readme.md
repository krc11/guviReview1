# Core Java Chat Application

A simple console-based Chat/User Registry Application built using **Core Java**, structured with a **layered architecture**, and using **File I/O** for persistence. This app demonstrates basic **CRUD operations**, **input validation**, and **error handling** in a clean modular format.

# Project Structure

ChatApplication/
│
├── src/
│ ├── model/ # Data models (e.g., User)
│ ├── repository/ # Handles CRUD operations using file I/O
│ ├── service/ # Business logic and validation
│ ├── controller/ # User interaction and input handling
│ ├── util/ # Input validation and helpers
│ └── Main.java # Application entry point
│
└── README.md # Project documentation

# Features

- Register a user with username and email.
- View a list of all registered users.
- Automatically creates necessary data files.
- Validates input (e.g., correct email format).
- Error messages for invalid actions or system issues.
- Clean and modular architecture (MVC-like).

---

# Technologies Used

- **Java 17+**
- File I/O (`FileWriter`, `BufferedReader`)
- IDE: IntelliJ IDEA / Eclipse

---

# How to Run

## 1. Prerequisites

- JDK 17 or higher
- Java-compatible IDE (e.g., IntelliJ, Eclipse)

## 2. Steps

1. **Clone or Download** this repository.
2. Open the project in your IDE.
3. Ensure `data/` folder is present or created automatically.
4. Run the `Main.java` class.

---

# Usage

On running the application, you’ll see a menu:
1. Register User

2. List Users

3. Exit


# Example Flow
Enter option: 1
Enter username: Alice
Enter email: alice@example.com
User registered successfully!

Enter option: 2
Alice - alice@example.com


---

# File Storage

User data is stored in:
data/users.txt

Each user is saved in a line as:
username,email


---

# Concepts Demonstrated

- Layered Architecture (Controller → Service → Repository)
- Java File I/O Operations
- String Parsing and Object Mapping
- Input Validation (Regex)
- Exception Handling
- Modular Code Design

---

# Possible Extensions

- Add socket-based chat functionality (Client/Server).
- Implement GUI using JavaFX or Swing.
- Support message history between users.
- Encrypt stored user data.

---

# License

This project is open-source and free to use for learning and development purposes.

---

## 👤 Author

Made by Kirti Raj








