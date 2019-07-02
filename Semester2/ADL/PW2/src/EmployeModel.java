
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EmployeModel {
    public void add(int id, String username, String password, String phone) {
        Connection con = new Connect().getConnection();
        String sql = "INSERT INTO users VALUES(?, ?, ?, ?);";
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setInt(1, id);
            prest.setString(2, username);
            prest.setString(3, password);
            prest.setString(4, phone);
            prest.executeUpdate();
        } catch(SQLException se) {
            System.out.println("Error msg: " + se.getMessage());
        }
    }
    
    public void edit(int id, String username, String password, String phone) {
        Connection con = new Connect().getConnection();
        String sql = "UPDATE users SET username=?, password=?, phone=? WHERE id=?;";
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, username);
            prest.setString(2, password);
            prest.setString(3, phone);
            prest.setInt(4, id);
            prest.executeUpdate();
        } catch(SQLException se) {
            System.out.println("Error msg: " + se.getMessage());
        }
    }

    public void delete(int id) {
        Connection con = new Connect().getConnection();
        String sql = "DELETE FROM users WHERE id=?;";
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setInt(1, id);
            prest.executeUpdate();
        } catch(SQLException se) {
            System.out.println("Error msg: " + se.getMessage());
        }
    }
}
