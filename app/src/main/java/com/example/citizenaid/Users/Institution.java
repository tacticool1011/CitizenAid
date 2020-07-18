package com.example.citizenaid.Users;

import com.google.android.gms.maps.model.LatLng;

public class Institution {
    private Institutions user;
    private LatLng pos;
    private String description, name, type;

    public Institution( Institutions user, LatLng pos, String description, String name, String type){
        this.user= user;
        this.pos = pos;
        this.type = type;
        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Institutions getUser() {
        return user;
    }
    public String getType(){
        return type;
    }
    public String getDescription() {
        return description;
    }

    public LatLng getPos() {
        return pos;
    }
}
