package com.example.citizenaid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.citizenaid.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_home);



        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

//        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                int id = menuItem.getItemId();
//                if (id == R.id.home){
////                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), ConversationsActivity.class ));
//                }
//                else if (id == R.id.logout){
////                    Toast.makeText(HomeActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class ));
//                } else if (id == R.id.find_friends){
//                    startActivity(new Intent(getApplicationContext(), FindFriendsActivity.class));
//                }
//                return true;
//            }
//        });
    }
}
