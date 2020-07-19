package com.example.citizenaid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import static com.example.citizenaid.ProfileActivity.description1;
import static com.example.citizenaid.ProfileActivity.name1;
import static com.example.citizenaid.ProfileActivity.type1;

import static com.example.citizenaid.MapsActivity.toDelete;

import static com.example.citizenaid.MapsActivity.marker1;
import static com.example.citizenaid.MapsActivity.notNull;
public class DetailsActivity extends AppCompatActivity {
    private TextView nam, desc, type;
    private ImageView image;
    private static String URL_GETEMAIL = LoginActivity.ngrokID+"/userCoordinates/getemail.php";
    private static String URL_GETPROFILE = LoginActivity.ngrokID+"/citizenAid/getprofile.php";
    LatLng latLng = MapsActivity.getClickPos();
    String email;

    private Button back, removelocation;
    public static boolean removed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nam = findViewById(R.id.institutionName);
        desc = findViewById(R.id.institutionDetails);
        type = findViewById(R.id.institutionType);
        image = findViewById(R.id.pfp);
        back = findViewById(R.id.detailsBackButton);
        System.out.println(MapsActivity.getClickPos());
        getEmail(MapsActivity.getClickPos().toString());
        int randomP = (int)(Math.random() * 8) + 1;
        if(randomP == 1){
            image.setImageResource(R.drawable.affordablehousing);
        } else if(randomP == 2){
            image.setImageResource(R.drawable.affordablehousing2);
        } else if(randomP == 3){
            image.setImageResource(R.drawable.soupkitchen);
        } else if(randomP == 4){
            image.setImageResource(R.drawable.soupkitchen2);
        } else if(randomP == 5){
            image.setImageResource(R.drawable.soupkitchen2);
        } else if(randomP == 6){
            image.setImageResource(R.drawable.foodbank);
        } else if(randomP == 7){
            image.setImageResource(R.drawable.foodbank2);
        } else {
            image.setImageResource(R.drawable.foodbank3);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                finish();
                return;
            }
        });


//        press.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getProfile(email);
//            }
//        });

    }

    private void getEmail(final String coordinate){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GETEMAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("CONNECTED");
                            Log.e("anyText",response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.optJSONArray("getemail");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.optJSONObject(i);
                                    String gotEmail  = object.getString("email").trim();
                                    email = gotEmail;
                                    getProfile(email);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DetailsActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            System.out.println(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println(error.toString());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("coordinate", coordinate);
                System.out.println(coordinate);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void getProfile(final String email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GETPROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("CONNECTED");
                            Log.e("anyText",response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.optJSONArray("getprofile");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.optJSONObject(i);
                                    String types  = object.getString("type");
                                    String description = object.getString("description");
                                    String name = object.getString("name");
                                    System.out.println(name);
                                    desc.setText(description);
                                    type.setText(types);
                                    nam.setText(name);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Toast.makeText(DetailsActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            System.out.println(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(DetailsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println(error.toString());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
