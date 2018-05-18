package com.java.example.JavaSBA_InvoiceProcess;

import java.util.ArrayList;
import java.util.List;

public class AuditorBO extends UserBO {

    @Override
    public List<Invoice> listInvoice() {
        InvoiceDAO invoiceDao = new InvoiceDAO();
        List<Invoice> invoices = invoiceDao.getAllInvoiceList();
        List<Invoice> paidInvoices = new ArrayList<>();
        for (Invoice inv : invoices) {
            if (inv.getStatus().equalsIgnoreCase("paid")) {
                paidInvoices.add(inv);
            }
        }
        return paidInvoices;
    }

    @Override
    public Integer createInvoice(Invoice invoice) throws InsufficientPrivilegeException {
        throw new InsufficientPrivilegeException("Permission Denied");
    }

    @Override
    Boolean updateInvoiceStatus(Invoice invoice, String status) {
        InvoiceDAO invoiceDao = new InvoiceDAO();
        invoiceDao.updateInvoiceStatus(invoice, status);
        return true;
    }

    @Override
    public Boolean invoicePayment(Invoice invoiceObj) throws InsufficientPrivilegeException {
        throw new InsufficientPrivilegeException("Permission Denied");
    }

}
