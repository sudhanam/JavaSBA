package com.java.example.JavaSBA_InvoiceProcess;

import java.util.Date;
import java.util.List;

public class Invoice {
    private Integer id;
    private String invoiceNumber;
    private String status;
    private Integer amount;
    private Date createdDate;
    private User createdBy;

    public Invoice(Integer id, String invoiceNumber, String status, Integer amount, Date createdDate, User createdBy) {
        super();
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.status = status;
        this.amount = amount;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public static Invoice getInvoiceById(Integer invoiceId, List<Invoice> invoiceList) {
        Invoice inv = null;
        for (Invoice invlst : invoiceList) {
            if (invlst.getId() == invoiceId) {
                return inv = invlst;
            }
        }
        return inv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
