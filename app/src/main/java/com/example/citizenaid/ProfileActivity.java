package com.example.citizenaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ProfileActivity  extends AppCompatActivity {

    DrawerLayout d1;
    Button button;
    EditText name, type, description;
    String mName, mType, mDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        d1 = findViewById(R.id.d1);


        button = findViewById(R.id.button);

        name = (EditText) findViewById(R.id.institution);
        type = (EditText) findViewById(R.id.institutionType);
        description = (EditText) findViewById(R.id.description);

        button = findViewById((R.id.button));
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.myLocation).setVisible(false);

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mName = name.getText().toString().trim();
                mType = type.getText().toString().trim();
                mDescription = description.getText().toString().trim();

                name.setText(mName);
                name.setText(mType);
                name.setText(mDescription);
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                
            }
        });


    }


}
