package com.example.citizenaid.Users;

public class Citizen implements User{
    private String firstName, lastName, email;
    private int zipCode;

    public Citizen(String firstName, String lastName, String email, int zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.zipCode = zipCode;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
