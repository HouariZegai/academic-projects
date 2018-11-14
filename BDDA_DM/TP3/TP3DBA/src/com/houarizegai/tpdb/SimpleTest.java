package com.houarizegai.tpdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleTest {

    static Connection con;
    
    static { // Initialize connection with loading class in JVM
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_dba", "root", "");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SimpleTest simpleTest = new SimpleTest();
        
        int number = 4;
        System.out.println("Factorial of " + number + " is: " + simpleTest.getFactorial(number));
    }
    
    public int getFactorial(int n) { // This method execute function in SGBD and get the result
        int fact = 0;
        try {
            String sql = "SELECT factorial(?) AS Fact;";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setInt(1, n);
            ResultSet rs = prest.executeQuery();
            if(rs.next())
                fact = rs.getInt("Fact");
        } catch(SQLException se) {
            se.printStackTrace();
        }
        return fact;
    }
}
