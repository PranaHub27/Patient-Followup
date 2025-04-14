package databasecon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ JDBC Driver Loaded Successfully.");

            // Connection URL (Ensure correct DB name & port)
            String url = "jdbc:mysql://localhost:3306/patientfollow?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String password = "Rolta@123";

            // Establish connection
            con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ ERROR: MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ ERROR: Database connection failed! Check database name, username, and password.");
            e.printStackTrace();
        }

        // Debugging: Check if connection is NULL
        if (con == null) {
            System.out.println("❌ ERROR: Connection object is NULL!");
        } else {
            System.out.println("✅ Connection object is NOT NULL.");
        }

        return con;
    }
}
