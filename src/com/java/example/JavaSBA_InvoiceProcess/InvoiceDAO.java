package com.java.example.JavaSBA_InvoiceProcess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDAO {

    public Integer invoiceCreation(Invoice invoiceObj) {
        try {
            Connection dbconn = DbConnection.getConnection();
            Statement stmt = dbconn.createStatement();

            String sql1 = "INSERT INTO invoice (invoice_number, status, amount, created_date, user_id) values ('" +
                    invoiceObj.getInvoiceNumber() + "','" +
                    invoiceObj.getStatus() + "'," +
                    invoiceObj.getAmount() + ",'" +
                    invoiceObj.getCreatedDate() + "'," +
                    invoiceObj.getCreatedBy().getId() + ")";

            stmt.executeUpdate(sql1);
//            String sql2 = "Select id from invoice where invoice_number = '" + invoiceObj.getInvoiceNumber() + "'";
//            ResultSet rs = stmt.executeQuery(sql2);
//            while (rs.next()) {
//                inv = rs.getInt("id");
//            }

//            ResultSet rs = stmt.getGeneratedKeys();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }

            return 1;
        } catch (Exception e) {
        }
        return 0;
    }


    public List<Invoice> getAllInvoiceList() {
        List<Invoice> invoices = new ArrayList<>();
        try {
            Connection dbconn = DbConnection.getConnection();
            Statement stmt = dbconn.createStatement();

            String sql2 = "Select id, username, password, address, role from user";
            ResultSet rs2 = stmt.executeQuery(sql2);
            List<User> users = new ArrayList<User>();
            while (rs2.next()) {
                User user = new User(rs2.getInt("id"), rs2.getString("username"), rs2.getString("password"), rs2.getString("address"), rs2.getString("role"));
                users.add(user);
            }


            String sql1 = "Select id, invoice_number, status, amount, created_date, user_id from invoice";
            ResultSet rs1 = stmt.executeQuery(sql1);

            while (rs1.next()) {
                ////////////// Verify all the below queries ///////////

                Integer id = rs1.getInt("id");
                String inv_num = rs1.getString("invoice_number");
                String status = rs1.getString("Status");
                Integer amount = rs1.getInt("amount");
                Date created = rs1.getDate("created_date");
                for(User user : users) {
                    if(user.getId().equals(rs1.getInt("user_id"))) {
                        Invoice inv = new Invoice(id, inv_num, status, amount, created, user);
                        invoices.add(inv);
                    }
                }
            }

        } catch (Exception e) {
        }
        return invoices;


    }

    public void updateInvoiceStatus(Invoice invoiceObj, String status) {
        try {
            Connection dbconn = DbConnection.getConnection();
            Statement stmt = dbconn.createStatement();
            String sql = "Update invoice set status ='" + status + "' where id = '" + invoiceObj.getId() + "'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
        }

    }

    public void invoicePayment(Invoice invoiceObj) {
        try {
            Connection dbconn = DbConnection.getConnection();
            Statement stmt = dbconn.createStatement();
            String sql = "Update invoice set status ='Paid' where id = '" + invoiceObj.getId() + "'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
        }

    }
}

