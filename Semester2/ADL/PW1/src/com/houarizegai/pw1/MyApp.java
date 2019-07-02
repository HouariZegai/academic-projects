package com.houarizegai.pw1;

import java.sql.*;

public class MyApp {

    public static void main(String[] args) {
        //editStudent(1, "Houari", "ZEGAI", true);
        //addStudent(2, "Younes", "CHARFAOUI", true);
        //addStudent(3, "Fatima", "KHANFAR", false);
        //deleteStudent(3);
        getAllStudents();
        //getAllStudentsWithThierPromotion();
    }

    // create
    public static void addStudent(int id, String firstName, String lastName, boolean gender) {
        Connection con = new DBConnection().getConnection();
        if(con == null)
            return;

        String sql = "INSERT INTO student VALUES (?, ?, ?, ?, null);";

        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setInt(1, id);
            prest.setString(2, firstName);
            prest.setString(3, lastName);
            prest.setBoolean(4, gender);
            int status = prest.executeUpdate();

            System.out.println("UPDATED !");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    // read
    public static void getAllStudents() {
        Connection con = new DBConnection().getConnection();
        if(con == null)
            return;

        String sql = "SELECT * FROM student;";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("First name: " + rs.getString("first_name"));
                System.out.println("Last name: " + rs.getString("last_name"));
                System.out.println("Gender: " + (rs.getBoolean("gender")? "Male" : "Female"));
                System.out.println("\n");
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    // update
    public static void editStudent(int id, String firstName, String lastName, boolean gender) {
        Connection con = new DBConnection().getConnection();
        if(con == null)
            return;

        String sql = "UPDATE student SET first_name=?, last_name=?, gender=? WHERE id=?;";

        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, firstName);
            prest.setString(2, lastName);
            prest.setBoolean(3, gender);
            prest.setInt(4, id);
            int status = prest.executeUpdate();

            System.out.println("UPDATED !");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    // delete
    public static void deleteStudent(int id) {
        Connection con = new DBConnection().getConnection();
        if(con == null)
            return;

        String sql = "DELETE FROM student WHERE id=?;";

        try {
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setInt(1, id);
            int status = prest.executeUpdate();

            System.out.println("DELETED !");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    // read
    public static void getAllStudentsWithThierPromotion() {
        Connection con = new DBConnection().getConnection();
        if(con == null)
            return;

        String sql = "SELECT first_name, last_name, gender, promotion FROM student, promotion WHERE student.id_promotion=promotion.id;";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("First name: " + rs.getString("first_name"));
                System.out.println("Last name: " + rs.getString("last_name"));
                System.out.println("Gender: " + (rs.getBoolean("gender")? "Male" : "Female"));
                System.out.println("Promotion: " + rs.getString("promotion"));
                System.out.println("\n");
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

}
