import java.sql.*;
import java.util.Scanner;

public class UpdatableResultSet {
    private Connection conn;
    private Statement stmt;

    public UpdatableResultSet(String url, String user, String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

    public void displayEmployees() {
        try (ResultSet rs = stmt.executeQuery("SELECT id, name, salary FROM employees")) {
            System.out.println("\nEmployee Details:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Salary: " + rs.getFloat("salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error displaying employees: " + e.getMessage());
        }
    }

    public void updateEmployeeSalary(String name, float newSalary) {
        try (ResultSet rs = stmt.executeQuery("SELECT id, name, salary FROM employees")) {
            boolean updated = false;
            while (rs.next()) {
                if (name.equalsIgnoreCase(rs.getString("name"))) {
                    rs.updateFloat("salary", newSalary);
                    rs.updateRow();
                    System.out.println("Updated salary of " + name + " to " + newSalary);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                System.out.println("Employee with name '" + name + "' not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating salary: " + e.getMessage());
        }
    }

    public void close() {
        try { if (stmt != null) stmt.close(); } catch (SQLException e) { }
        try { if (conn != null) conn.close(); } catch (SQLException e) { }
    }

    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/IIIITASec";  // Change DB name
        final String user = "root";                               // Change DB username
        final String password = "";                       // Change DB password

        Scanner sc = new Scanner(System.in);
        UpdatableResultSet app = null;

        try {
            app = new UpdatableResultSet(url, user, password);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n===== Employee Management Menu =====");
                System.out.println("1. Display all employees");
                System.out.println("2. Update employee salary");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        app.displayEmployees();
                        break;
                    case 2:
                        System.out.print("Enter employee name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter new salary: ");
                        float salary = sc.nextFloat();
                        sc.nextLine(); // consume newline

                        app.updateEmployeeSalary(name, salary);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (app != null) {
                app.close();
            }
            sc.close();
        }
    }
}
