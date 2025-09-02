import java.sql.*;
import java.util.Scanner;

public class JDBC3 {

    static String url = "jdbc:mysql://localhost:3306/iiiitasec";
    static String user = "root";
    static String password = "";
    static Connection conn;

    

    // Method to insert a new employee
    public static void insertEmployee(Scanner sc) {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter Department: ");
            String dept = sc.nextLine();

            String insert = "{CALL insert_employee(?, ?, ?, ?)}";
            try (CallableStatement cs = conn.prepareCall(insert)) {
                cs.setInt(1, id);
                cs.setString(2, name);
                cs.setDouble(3, salary);
                cs.setString(4, dept);
                cs.execute();
                System.out.println(" Employee inserted successfully.");
            }
        } catch (Exception e) {
            System.out.println(" Error inserting employee.");
            e.printStackTrace();
        }
    }

    // Method to get salary for a given employee ID
    public static void getSalaryById(Scanner sc) {
        try {
            System.out.print("Enter Employee ID: ");
            int empId = sc.nextInt();

            String getSalary = "{CALL get_salary(?, ?)}";
            try (CallableStatement cs = conn.prepareCall(getSalary)) {
                cs.setInt(1, empId);
                cs.registerOutParameter(2, Types.DOUBLE);
                cs.execute();

                double empSalary = cs.getDouble(2);
                System.out.println(" Salary: " + empSalary);
            }
        } catch (Exception e) {
            System.out.println(" Error retrieving salary.");
            e.printStackTrace();
        }
    }

    // Method to display all employee records
    public static void displayAllEmployees() {
        String select = "SELECT * FROM employee";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(select)) {

            System.out.println("\nEmpID\tName\t\tSalary\t\tDepartment");
            System.out.println("----------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                String dept = rs.getString("department");

                System.out.printf("%d\t%-10s\t%.2f\t%-10s%n", id, name, salary, dept);
            }
        } catch (SQLException e) {
            System.out.println("Error displaying employees.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(" Connected to database.");

            int choice;
            do {
                System.out.println("\n===== Employee Menu =====");
                System.out.println("1. Insert Employee");
                System.out.println("2. Get Salary by Employee ID");
                System.out.println("3. Display All Employees");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        insertEmployee(sc);
                        break;
                    case 2:
                        getSalaryById(sc);
                        break;
                    case 3:
                        displayAllEmployees();
                        break;
                    case 4:
                        System.out.println(" Exiting...");
                        break;
                    default:
                        System.out.println(" Invalid choice.");
                }

            } while (choice != 4);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
