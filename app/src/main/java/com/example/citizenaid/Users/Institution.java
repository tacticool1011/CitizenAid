package com.example.citizenaid.Users;

import com.google.android.gms.maps.model.LatLng;

public class Institution {
    private Institutions institute;
    private LatLng pos;

    public Institution(Institutions institute, LatLng pos){
        this.institute = institute;
        this.pos = pos;
    }

    public Institutions getInstitute() {
        return institute;
    }

    public LatLng getPos() {
        return pos;
    }
}
