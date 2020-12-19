package com.elevatelab.ontimepro.models;

public class UserIndividual {

    private final String name;
    private final String organization;
    private final String email;
    private final String password;


    public UserIndividual(String name, String organization, String email, String password) {
        this.name = name;
        this.organization = organization;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }


    public String getMobile() {
        return organization;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

}
