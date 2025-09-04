import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC4 {
    public static void main(String[] args) {
        DatabaseNavigator navigator = new DatabaseNavigator();
        if (navigator.connect()) {
            navigator.navigate();
            navigator.close();
        } else {
            System.out.println("Failed to connect to database. Exiting...");
        }
    }
}

class DatabaseNavigator {
    private static final String URL = "jdbc:mysql://localhost:3307/IIIITASec"; // Change to your DB name
    private static final String USER = "root";   // XAMPP default user
    private static final String PASSWORD = "";   // XAMPP default empty password

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private Scanner scanner;

    public boolean connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = stmt.executeQuery("SELECT * FROM employees");
            scanner = new Scanner(System.in);
            System.out.println("Connected to database successfully.");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Include it in your library path.");
            return false;
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
            return false;
        }
    }

    public void navigate() {
        if (rs == null) {
            System.out.println("ResultSet is not initialized.");
            return;
        }

        boolean running = true;
        while (running) {
            printMenu();
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
                continue;
            }

            switch (choice) {
                case 1 -> next();
                case 2 -> previous();
                case 3 -> first();
                case 4 -> last();
                case 5 -> absolute();
                case 6 -> beforeFirst();
                case 7 -> afterLast();
                case 8 -> getCurrentRow();
                case 9 -> {
                    running = false;
                    System.out.println("Exiting program.");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n========== Scrollable ResultSet Menu ==========");
        System.out.println("1. Next");
        System.out.println("2. Previous");
        System.out.println("3. First");
        System.out.println("4. Last");
        System.out.println("5. Absolute (go to specific row)");
        System.out.println("6. Before First");
        System.out.println("7. After Last");
        System.out.println("8. Get Current Row Number");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private void next() {
        try {
            if (rs.next()) {
                displayRow();
            } else {
                System.out.println("Already at the end.");
            }
        } catch (SQLException e) {
            System.out.println("Error moving next: " + e.getMessage());
        }
    }

    private void previous() {
        try {
            if (rs.previous()) {
                displayRow();
            } else {
                System.out.println("Already at the beginning.");
            }
        } catch (SQLException e) {
            System.out.println("Error moving previous: " + e.getMessage());
        }
    }

    private void first() {
        try {
            if (rs.first()) {
                displayRow();
            } else {
                System.out.println("Cannot move to first row.");
            }
        } catch (SQLException e) {
            System.out.println("Error moving first: " + e.getMessage());
        }
    }

    private void last() {
        try {
            if (rs.last()) {
                displayRow();
            } else {
                System.out.println("Cannot move to last row.");
            }
        } catch (SQLException e) {
            System.out.println("Error moving last: " + e.getMessage());
        }
    }

    private void absolute() {
        try {
            System.out.print("Enter row number: ");
            int row = scanner.nextInt();
            if (rs.absolute(row)) {
                displayRow();
            } else {
                System.out.println("Invalid row number.");
            }
        } catch (SQLException e) {
            System.out.println("Error moving absolute: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input for row number.");
            scanner.nextLine(); // clear invalid input
        }
    }

    private void beforeFirst() {
        try {
            rs.beforeFirst();
            System.out.println("Cursor moved before first row.");
        } catch (SQLException e) {
            System.out.println("Error moving beforeFirst: " + e.getMessage());
        }
    }

    private void afterLast() {
        try {
            rs.afterLast();
            System.out.println("Cursor moved after last row.");
        } catch (SQLException e) {
            System.out.println("Error moving afterLast: " + e.getMessage());
        }
    }

    private void getCurrentRow() {
        try {
            int row = rs.getRow();
            if (row == 0) {
                System.out.println("Cursor is not on a valid row.");
            } else {
                System.out.println("Current Row: " + row);
            }
        } catch (SQLException e) {
            System.out.println("Error getting current row: " + e.getMessage());
        }
    }

    private void displayRow() throws SQLException {
        System.out.println("Row " + rs.getRow() +
                ": ID = " + rs.getInt("id") +
                ", Name = " + rs.getString("name") +
                ", Position = " + rs.getString("position"));
    }

    public void close() {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.out.println("Error closing ResultSet: " + e.getMessage());
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.out.println("Error closing Statement: " + e.getMessage());
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing Connection: " + e.getMessage());
        }
        if (scanner != null) scanner.close();

        System.out.println("Resources released successfully.");
    }
}
