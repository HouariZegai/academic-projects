package com.houarizegai.pw1;

import java.sql.*;

public class DBConnection {
    private static final String USER = "houari";
    private static final String PASS = "1234";
    private static final String HOST = "192.168.43.36";
    private static final int PORT = 3306;
    private static final String DB_NAME = "ADWeb_db";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME /*+ "?useSSL=false&serverTimezone=UTC"*/;

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);

            System.out.println("Connected!");
        } catch(SQLException se) {
            System.out.println("Connection error !");
            se.printStackTrace();
        }

        return con;
    }

    public static void main(String[] args) {
        new DBConnection().getConnection();
    }

}
