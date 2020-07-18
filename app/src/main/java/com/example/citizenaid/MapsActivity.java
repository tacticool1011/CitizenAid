package com.example.citizenaid;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.citizenaid.Users.Institution;
import com.example.citizenaid.Users.Institutions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Button addlocation;
    private GoogleMap mMap;
    private Marker selected;
    private boolean clicked = false;
    private static LatLng clickPos;
    //public static Institutions institute;
    public static boolean addedanything = false;
    public static  Institutions institute = new Institutions("bob", "farm", "bob@gmail.com", 5, 12345);
    private boolean selectedAnything = false, selectedMarker = false;
    DrawerLayout d1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        d1 = findViewById(R.id.d1);

        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
//                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class ));
                }
                else if (id == R.id.profile){
//                    Toast.makeText(HomeActivity.this, "Post", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class ));
                }
                else if (id == R.id.logout) {
//                    Toast.makeText(HomeActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                return true;
            }
        });
        addlocation = findViewById(R.id.addlocation);
        addlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked){
                    startActivity(new Intent(getApplicationContext(), InstitutionActivity.class));
                    finish();
                    return;
                }
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if(addedanything) {
            for (Institution i : institute.getLocations()) {
                LatLng institutePos = i.getPos();
                Marker m = mMap.addMarker(new MarkerOptions().position(institutePos).title("Your Institute"));
            }
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(selected != null){
                    selected.remove();
                }

                clickPos = latLng;
                selectedAnything = true;

                selected = mMap.addMarker(new MarkerOptions().position(latLng).title(""));
                clicked = true;

            }

        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                clicked = true;
                return clicked;
            }
        });
    }
    public static LatLng getClickPos() {
        return clickPos;
    }
}