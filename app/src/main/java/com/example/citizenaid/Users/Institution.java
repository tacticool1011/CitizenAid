package com.example.citizenaid.Users;

import com.google.android.gms.maps.model.LatLng;

public class Institution {
    private Institutions user;
    private LatLng pos;
    private String description, name;

    public Institution( Institutions user, LatLng pos, String description, String name){
        this.user= user;
        this.pos = pos;
        this.description = description;
        this.name = name;
    }



    public LatLng getPos() {
        return pos;
    }
}
