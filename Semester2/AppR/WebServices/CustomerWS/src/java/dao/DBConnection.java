package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DB_NAME = "web_customer_tracker";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    
    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=false&serverTimezone=UTC", USERNAME, PASSWORD);
            System.out.println("Connected!");
        } catch(SQLException se) {
            se.printStackTrace();
        }
        
        return con;
    }
}
