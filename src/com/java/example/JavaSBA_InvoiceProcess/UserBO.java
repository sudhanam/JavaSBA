package com.java.example.JavaSBA_InvoiceProcess;

import java.sql.SQLException;
import java.util.List;

abstract class UserBO {
    List<Invoice> invoices;
    Integer inv;
    Boolean InvoiceStatus;
    Boolean invoicePayment;

    public static User validateUser(String userName, String password) {
        UserDAO userDAO = new UserDAO();
        try {
            List<User> users = userDAO.getAllUser();

            for (User user : users) {
                if (user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equals(password)) {
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    abstract List<Invoice> listInvoice() throws InsufficientPrivilegeException;

    abstract Integer createInvoice(Invoice invoice) throws InsufficientPrivilegeException;

    abstract Boolean updateInvoiceStatus(Invoice invoice, String status) throws InsufficientPrivilegeException;

    abstract Boolean invoicePayment(Invoice invoiceObj) throws InsufficientPrivilegeException;
}