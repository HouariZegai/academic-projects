package com.houarizegai;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Extraction {
    static List<Person> data;

    /* Start DB Information */
    public static final String DB_NAME = "tp4_sma";
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 3306;
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    /* End DB Information */

    private static Connection con = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USERNAME, PASSWORD);
            System.out.println("Connected !");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public Extraction(String queries) {
        //String sql = "SELECT name, surname, birthdate FROM person;";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queries);

            data = new LinkedList<>();
            while (rs.next()) {
                Person person = new Person();
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setBirthdate(rs.getDate("birthdate"));
                //System.out.println(person);

                data.add(person);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

}