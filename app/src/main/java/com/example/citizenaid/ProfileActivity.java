package com.example.citizenaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.citizenaid.Users.Citizen;
import com.example.citizenaid.Users.Institutions;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity  extends AppCompatActivity {

    DrawerLayout d1;
    Button button;
    TextView name2, type2, description2;
    public static String name1 = "", type1 = "", description1 = "";
    EditText name, type, description;
    String mName, mType, mDescription;
    Citizen citizen = LoginActivity.getCitizen();
    Institutions institutions = LoginActivity.getInstitutions();
    String userr;
    private static String URL_PROFILE = "http://2fe49e011188.ngrok.io/citizenAid/profile.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        d1 = findViewById(R.id.d1);


        button = findViewById(R.id.confirm);
        name2 = findViewById(R.id.institutionName2);
        type2 = findViewById(R.id.institutionType2);
        description2 = findViewById(R.id.description2);
        name = (EditText) findViewById(R.id.institutionName);
        type = (EditText) findViewById(R.id.institutionType);
        description = (EditText) findViewById(R.id.description);

        if (citizen.getEmail().equals("notcitizen")){
            userr = "institutions";
        } else {
            userr = "citizen";
        }



        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.getMenu().findItem(R.id.myLocation).setVisible(false);

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


                name2.setText(name.getText().toString());
                type2.setText(type.getText().toString());
                description2.setText(description.getText().toString());
                name1 = name2.getText().toString();
                type1 = type2.getText().toString();
                description1 = description2.getText().toString();
                name.setVisibility(View.GONE);
                type.setVisibility(View.GONE);
                description.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, "Created a profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                updateProfile(institutions.getEmail(), mType, mDescription);
//                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                
            }
        });


    }

    private void updateProfile(final String email, final String type, final String description){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");


                            if (success.equals("1")){
//                                Toast.makeText(ainActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(ProfileActivity.this, "not good", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("type", type);
                params.put("description", description);
                System.out.println(description);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
