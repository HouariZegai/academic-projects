package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.Customer;

public class CustomerDao {

    public static List<Customer> getCustomers() {
        Connection con = new DBConnection().getConnection();
        if (con == null) {
            return null;
        }
        String sql = "SELECT * FROM customer;";
        List<Customer> customers = new LinkedList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email")));
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        
        return customers;
    }

    public static int deleteClient(int id) {
        Connection con = new DBConnection().getConnection();
        if (con == null) {
            return -1;
        }
        String sql = "DELETE FROM customer WHERE id=?;";
        List<Customer> customers = new LinkedList<>();
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setInt(1, id);
            
            return prest.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
            return 0;
        }
    }

    public static int addCustomer(Customer customer) {
        Connection con = new DBConnection().getConnection();
        if (con == null) {
            return -1;
        }
        
        String sql = "INSERT INTO customer (first_name, last_name, email) VALUES (?, ?, ?);";
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, customer.getFirstName());
            prest.setString(2, customer.getLastName());
            prest.setString(3, customer.getEmail());
            
            return prest.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
            return 0;
        }
    }

    public static int editCustomer(Customer customer) {
        Connection con = new DBConnection().getConnection();
        if (con == null) {
            return -1;
        }
        
        String sql = "UPDATE customer SET first_name=?, last_name = ?, email = ? WHERE id = ?;";
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, customer.getFirstName());
            prest.setString(2, customer.getLastName());
            prest.setString(3, customer.getEmail());
            prest.setInt(4, customer.getId());
            
            return prest.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
            return 0;
        }
    }
    
}
