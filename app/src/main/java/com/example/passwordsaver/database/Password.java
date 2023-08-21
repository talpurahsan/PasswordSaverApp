package com.example.passwordsaver.database;


public class Password {

    private int id;
    private String title;
    private  String account;
    private String username;
    private String password;
    private String passwordType;

    public Password(int id, String title, String account, String username, String password, String passwordType) {
        this.id = id;
        this.title = title;
        this.account = account;
        this.username = username;
        this.password = password;
        this.passwordType = passwordType;
    }

    public Password(String title, String account, String username, String password, String passwordType) {
        this.title = title;
        this.account = account;
        this.username = username;
        this.password = password;
        this.passwordType = passwordType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }
}
