package com.java.example.JavaSBA_InvoiceProcess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        User user;
        UserBO userBO;
        String status;
        Boolean logout;
        Invoice invoice;
        Integer statusId;
        Integer invoiceId;
        String newLine = "\n";
        String invoiceDetails;
        List<Invoice> invoiceList = new ArrayList<Invoice>();
        StringBuilder mainMenu = new StringBuilder();
        StringBuilder userMenu = new StringBuilder();
        StringBuilder auditorMenu = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mainMenu.append("1. Login").append(newLine)
                .append("2. Exit").append(newLine)
                .append("Enter the choice:");
        userMenu.append("1. Create invoice").append(newLine)
                .append("2. Update invoice").append(newLine)
                .append("3. Invoice Payment").append(newLine)
                .append("4. Logout").append(newLine)
                .append("Enter the choice:");
        auditorMenu.append("1. Approved").append(newLine)
                .append("2. Deny");
        List<String> auditorList = new ArrayList<String>() {
            {
                add("Approved");
                add("Deny");
            }
        };
        while (true) {
            System.out.println(mainMenu.toString());
            int choice = new Integer(br.readLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter the username:");
                    String userName = br.readLine();
                    System.out.println("Enter the password:");
                    String password = br.readLine();

                    user = UserBO.validateUser(userName, password);
                    if (user != null) {
                        logout = false;

                        while (true) {
                            System.out.println(userMenu.toString());
                            int userChoice = new Integer(br.readLine());
                            switch (userChoice) {
                                case 1:
                                    try {
                                        System.out.println("Enter the invoice detail:");
                                        invoiceDetails = br.readLine();
                                        // fill the code

                                        if (user.getRole().equalsIgnoreCase("Clerk")) {
                                            String[] invoiceInput = invoiceDetails.split(",");
                                            Invoice invoice1 = new Invoice(0, invoiceInput[0], "Pending", Integer.valueOf(invoiceInput[1]), Date.valueOf(invoiceInput[2]), user);

                                            InvoiceDAO invoiceDAO = new InvoiceDAO();
                                            if (invoiceDAO.invoiceCreation(invoice1) > 0) {
                                                System.out.println("Invoice created successfully");
                                            }
                                        } else {
                                            throw new InsufficientPrivilegeException("Permission Denied");
                                        }

                                    } catch (Exception e) {
//                                        System.out.println(e.toString());
                                    }
                                    break;
                                case 2:
                                    try {
                                        //fill the code

                                        InvoiceDAO invoiceDAO = new InvoiceDAO();
                                        invoiceList = invoiceDAO.getAllInvoiceList();

                                        if (user.getRole().equalsIgnoreCase("Auditor")) {


                                            if (!invoiceList.isEmpty()) {
                                                System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", "Id",
                                                        "Invoice number", "Amount",
                                                        "Status", "Created by",
                                                        "Created on");
                                                //fill the code here

                                                for (Invoice inv : invoiceList) {
                                                    if (!inv.getStatus().equalsIgnoreCase("Paid")) {
                                                        System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", inv.getId(),
                                                                inv.getInvoiceNumber(),
                                                                inv.getAmount(),
                                                                inv.getStatus(),
                                                                inv.getCreatedBy().getUserName(),
                                                                inv.getCreatedDate());
                                                    }
                                                }

                                                System.out.println("Enter the id to update the status:");
                                                invoiceId = new Integer(br.readLine());
                                                Invoice statusInv = null;

                                                boolean matched = false;
                                                for (Invoice inv : invoiceList) {
                                                    if (invoiceId.equals(inv.getId())) {
                                                        matched = true;
                                                        statusInv = inv;
                                                    }
                                                }
                                                if (matched) {
                                                    System.out.println(auditorMenu.toString());
                                                    System.out.println("Enter the status number:");
                                                    statusId = new Integer(br.readLine());

                                                    if (statusId == 1) {
                                                        invoiceDAO.updateInvoiceStatus(statusInv, "Approved");
                                                        System.out.println("Invoice updated successfully");
                                                    } else if (statusId == 2) {
                                                        invoiceDAO.updateInvoiceStatus(statusInv, "Deny");
                                                        System.out.println("Invoice updated successfully");
                                                    } else {
                                                        System.out.println("Status Id is invalid");
                                                    }
                                                } else {
                                                    System.out.println("Invoice Id is invalid");
                                                }
                                            }
                                        } else if (user.getRole().equalsIgnoreCase("Payment Releaser")) {
                                            if (!invoiceList.isEmpty()) {
                                                System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", "Id",
                                                        "Invoice number", "Amount",
                                                        "Status", "Created by",
                                                        "Created on");
                                                //fill the code here

                                                for (Invoice inv : invoiceList) {
                                                    if (inv.getStatus().equalsIgnoreCase("Approved")) {
                                                        System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", inv.getId(),
                                                                inv.getInvoiceNumber(),
                                                                inv.getAmount(),
                                                                inv.getStatus(),
                                                                inv.getCreatedBy().getUserName(),
                                                                inv.getCreatedDate());
                                                    }
                                                }

                                                System.out.println("Enter the id to update the status:");
                                                invoiceId = new Integer(br.readLine());
                                                Invoice statusInv = null;

                                                boolean matched = false;
                                                for (Invoice inv : invoiceList) {
                                                    if (invoiceId.equals(inv.getId())) {
                                                        matched = true;
                                                        statusInv = inv;
                                                    }
                                                }
                                                if (matched) {
                                                    System.out.println(auditorMenu.toString());
                                                    System.out.println("Enter the status number:");
                                                    statusId = new Integer(br.readLine());
                                                    throw new InsufficientPrivilegeException("Permission Denied");
                                                } else {
                                                    System.out.println("Invoice Id is invalid");
                                                }
                                            }
                                        }
                                        else {
                                            throw new InsufficientPrivilegeException("Permission Denied");
                                        }

                                    } catch (Exception e) {
//                                        System.out.println(e.toString());
                                    }
                                    break;
                                case 3:
                                    try {
                                        // fill the code here
                                        InvoiceDAO invoiceDAO = new InvoiceDAO();
                                        invoiceList = invoiceDAO.getAllInvoiceList();

//                                        for(Invoice invoice1 : invoiceListInitial) {
//                                            if(invoice1.getStatus().equalsIgnoreCase("Approved")) {
//                                                invoiceList.add(invoice1);
//                                            }
//                                        }

                                        if (!invoiceList.isEmpty()) {
                                            System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", "Id",
                                                    "Invoice number", "Amount",
                                                    "Status", "Created by",
                                                    "Created on");
                                            //fill the code here

                                            for (Invoice inv : invoiceList) {
                                                if (user.getRole().equalsIgnoreCase("Payment Releaser") && inv.getStatus().equalsIgnoreCase("Approved")) {
                                                    System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", inv.getId(),
                                                            inv.getInvoiceNumber(),
                                                            inv.getAmount(),
                                                            inv.getStatus(),
                                                            inv.getCreatedBy().getUserName(),
                                                            inv.getCreatedDate());
                                                }
                                                if (user.getRole().equalsIgnoreCase("Auditor") && !inv.getStatus().equalsIgnoreCase("Paid")) {
                                                    System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", inv.getId(),
                                                            inv.getInvoiceNumber(),
                                                            inv.getAmount(),
                                                            inv.getStatus(),
                                                            inv.getCreatedBy().getUserName(),
                                                            inv.getCreatedDate());
                                                }
                                            }

                                            System.out.println("Enter the id to Pay::");
                                            invoiceId = new Integer(br.readLine());

                                            Invoice payment = null;
                                            boolean matched = false;
                                            for (Invoice inv : invoiceList) {
                                                if (invoiceId.equals(inv.getId())) {
                                                    matched = true;
                                                    payment = inv;
                                                }
                                            }
                                            if (matched) {
                                                // fill teh code here
                                                if (user.getRole().equalsIgnoreCase("Payment Releaser")) {

                                                    invoiceDAO.invoicePayment(payment);
                                                    System.out.println("Invoice paid successfully");

                                                } else {
                                                    throw new InsufficientPrivilegeException("Permission Denied");
                                                }

                                            } else {
                                                System.out.println("Invoice Id is invalid");
                                            }
                                        }

                                    } catch (Exception e) {
//                                        System.out.println(e.toString());
                                    }
                                    break;
                                case 4:
                                    logout = true;
                                    break;
                            }
                            if (logout)
                                break;
                        }
                    } else
                        System.out.println("Invalid username or password");
                    break;
                case 2:
                    System.exit(0);
            }

        }

    }

    public static UserBO findRole(User user) {
        String role = user.getRole();
        UserBO userBo = null;
        if (role.equalsIgnoreCase("clerk")) {
            userBo = new ClerkBO();

        }
        if (role.equalsIgnoreCase("payment released")) {
            userBo = new PaymentReleaserBO();
        }
        if (role.equalsIgnoreCase("auditor")) {
            userBo = new AuditorBO();
        }
        return userBo;
    }

}