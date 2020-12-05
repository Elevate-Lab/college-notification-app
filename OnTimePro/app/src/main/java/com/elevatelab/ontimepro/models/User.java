package com.elevatelab.ontimepro.models;

public class User{

    private String name;
    private String mobile;
    private String email;
    private String password;


    public User(String name, String mobile ,String email, String password) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }


    public String getMobile() {
        return mobile;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

}
