package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC", "root", "root");
            System.out.println("Connected!");
        } catch(SQLException se) {
            se.printStackTrace();
        }
        
        return con;
    }
}
