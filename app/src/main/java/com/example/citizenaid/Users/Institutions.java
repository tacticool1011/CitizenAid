package com.example.citizenaid.Users;

import java.util.ArrayList;

public class Institutions{
    private String name, type, email;
    private int rating, zipcode;
    private ArrayList<Institution> locations = new ArrayList<>();
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

    public void addLocations(Institution i){
        locations.add(i);
    }
    public ArrayList<Institution> getLocations(){
        return locations;
    }
}
