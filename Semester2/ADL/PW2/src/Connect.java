
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
    
    Connection con = null;
    public Connection getConnection() {
        try {
            // load and register JDBC driver for MySQL
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adl_tp_db?useSSL=false&serverTimezone=UTC", "root", "root");

            System.out.println("Connected!");
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        new Connect().getConnection();
    }
}
