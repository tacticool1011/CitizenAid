package com.example.citizenaid.Users;

public class Institutions {
    private String name, type, email;
    private int rating, zipcode;

    public Institutions(String name, String type, String email, int rating, int zipcode){
        this.name = name;
        this.type = type;
        this.email = email;
        this.rating = rating;
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getType() {
        return type;
    }
}
