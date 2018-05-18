package com.java.example.JavaSBA_InvoiceProcess;

import java.util.List;

public class ClerkBO extends UserBO {

    @Override
    List<Invoice> listInvoice() throws InsufficientPrivilegeException {
        throw new InsufficientPrivilegeException("Permission Denied");
    }


    @Override
    Integer createInvoice(Invoice invoice) {
        return new InvoiceDAO().invoiceCreation(invoice);
    }

    @Override
    Boolean updateInvoiceStatus(Invoice invoice, String status) throws InsufficientPrivilegeException {
        throw new InsufficientPrivilegeException("Permission Denied");
    }

    @Override
    Boolean invoicePayment(Invoice invoiceObj) throws InsufficientPrivilegeException {
        throw new InsufficientPrivilegeException("Permission Denied");
    }

}
