import java.sql.*;
import java.util.Scanner;

class JDBC1 {
    Connection con;
    Statement stmt;
    String url = "jdbc:mysql://localhost:3307/IIIITASec";
    String name = "root";
    String password = "";

    public void myconnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, name, password);
            stmt = con.createStatement();
            System.out.println("Connection Success...................");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
    }

    public void myinsert(String sname, String rollno, String branch, String section,
                         String subject1, String subject2, String subject3, String subject4, String subject5) {
        try {
            String query = String.format(
                "INSERT INTO student1(sname, rollno, branch, section, subject1, subject2, subject3, subject4, subject5) " +
                "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                sname, rollno, branch, section, subject1, subject2, subject3, subject4, subject5
            );
            int x = stmt.executeUpdate(query);
            System.out.println(x + " record(s) inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Insertion Error: " + e);
        }
    }

    public void myupdate(String rollno, String sname) {
        try {
            String query = String.format("UPDATE student1 SET sname='%s' WHERE rollno='%s'", sname, rollno);
            int x = stmt.executeUpdate(query);
            if (x == 0) {
                System.out.println("No record found with rollno = " + rollno);
            } else {
                System.out.println(x + " record(s) updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Update Error: " + e);
        }
    }

    public void mydelete(String rollno) {
        try {
            String query = String.format("DELETE FROM student1 WHERE rollno='%s'", rollno);
            int x = stmt.executeUpdate(query);
            if (x == 0) {
                System.out.println("No record found with rollno = " + rollno);
            } else {
                System.out.println(x + " record(s) deleted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Delete Error: " + e);
        }
    }

    private String getClassByPercentage(double percentage) {
        if (percentage >= 60)
            return "First Class";
        else if (percentage >= 50)
            return "Second Class";
        else if (percentage >= 35)
            return "Third Class";
        else
            return "Fail";
    }

    public void displayrecords() {
        try {
            String query = "SELECT * FROM student1";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("sname\trollno\tbranch\tsection\tsub1\tsub2\tsub3\tsub4\tsub5\ttotal\tpercentage\tclass");
            while (rs.next()) {
                String sname = rs.getString("sname");
                String rollno = rs.getString("rollno");
                String branch = rs.getString("branch");
                String section = rs.getString("section");

                int subject1 = Integer.parseInt(rs.getString("subject1"));
                int subject2 = Integer.parseInt(rs.getString("subject2"));
                int subject3 = Integer.parseInt(rs.getString("subject3"));
                int subject4 = Integer.parseInt(rs.getString("subject4"));
                int subject5 = Integer.parseInt(rs.getString("subject5"));

                int total = subject1 + subject2 + subject3 + subject4 + subject5;
                double percentage = total / 5.0;
                String className = getClassByPercentage(percentage);

                System.out.printf("%s\t%s\t%s\t%s\t%d\t%d\t%d\t%d\t%d\t%d\t%.2f\t%s\n",
                        sname, rollno, branch, section,
                        subject1, subject2, subject3, subject4, subject5,
                        total, percentage, className);
            }
        } catch (SQLException e) {
            System.out.println("Display Error: " + e);
        } catch (NumberFormatException ne) {
            System.out.println("Error parsing marks to numbers: " + ne);
        }
    }

    public static void main(String args[]) {
        JDBC1 obj = new JDBC1();
        obj.myconnect();
        System.out.println("Current Student1 Table records are...................");
        obj.displayrecords();

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("stop")) {
            System.out.println("\nEnter command \n1.insert, \n2.update, \n3.delete, \n4.display, \n5.stop : ");
            input = sc.nextLine();

            switch (input.toLowerCase()) {
                case "1":
                    for (int i = 1; i <= 2; i++) {
                        System.out.println("\nEntering record #" + i);

                        System.out.print("Enter sname: ");
                        String sname = sc.nextLine();

                        System.out.print("Enter rollno: ");
                        String rollno = sc.nextLine();

                        System.out.print("Enter branch: ");
                        String branch = sc.nextLine();

                        System.out.print("Enter section: ");
                        String section = sc.nextLine();

                        System.out.print("Enter marks for subject1 (integer): ");
                        String subject1 = sc.nextLine();

                        System.out.print("Enter marks for subject2 (integer): ");
                        String subject2 = sc.nextLine();

                        System.out.print("Enter marks for subject3 (integer): ");
                        String subject3 = sc.nextLine();

                        System.out.print("Enter marks for subject4 (integer): ");
                        String subject4 = sc.nextLine();

                        System.out.print("Enter marks for subject5 (integer): ");
                        String subject5 = sc.nextLine();

                        obj.myinsert(sname, rollno, branch, section, subject1, subject2, subject3, subject4, subject5);
                    }
                    break;

                case "2":
                    System.out.print("Enter rollno of the student to update: ");
                    String rollnoUpdate = sc.nextLine();
                    System.out.print("Enter new sname: ");
                    String newName = sc.nextLine();
                    if (rollnoUpdate.isEmpty() || newName.isEmpty()) {
                        System.out.println("rollno and new sname must not be empty.");
                    } else {
                        obj.myupdate(rollnoUpdate, newName);
                    }
                    break;

                case "3":
                    System.out.print("Enter rollno of the student to delete: ");
                    String rollnoDelete = sc.nextLine();
                    if (rollnoDelete.isEmpty()) {
                        System.out.println("rollno must not be empty.");
                    } else {
                        obj.mydelete(rollnoDelete);
                    }
                    break;

                case "4":
                    obj.displayrecords();
                    break;

                case "5":
                    System.out.println("Exiting program.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command.");
            }
        }
        sc.close();
    }
}
