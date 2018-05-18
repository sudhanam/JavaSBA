package com.java.example.JavaSBA_InvoiceProcess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;


public class DbConnection {
    public static Connection getConnection() {

        Connection connection = null;
        try {

            ResourceBundle rb = ResourceBundle.getBundle("com.java.example.JavaSBA_InvoiceProcess.mysql");
            String pass = rb.getString("db.password");
            String user = rb.getString("db.username");
            String url = rb.getString("db.url");

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            return connection;

        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}

