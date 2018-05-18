package com.java.example.JavaSBA_InvoiceProcess;

public class User {

    private Integer id;
    private String userName;
    private String password;
    private String address;
    private String role;

    public User(Integer id, String userName, String password, String address, String role) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}