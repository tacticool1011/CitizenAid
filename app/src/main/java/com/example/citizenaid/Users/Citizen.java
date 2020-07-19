package com.example.citizenaid.Users;

public class Citizen{
    private String name, email;
    private int zipCode;

    public Citizen(String name, String email, int zipCode){
        this.name = name;
        this.email = email;
        this.zipCode = zipCode;
    }

    public Citizen (String name, String email){
        this.name = name;
        this.email = email;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
