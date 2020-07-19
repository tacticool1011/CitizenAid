package com.example.citizenaid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class SplashPage extends AppCompatActivity {
    private static String URL_GETCOOR = LoginActivity.ngrokID+"/userCoordinates/getcoordinates.php";
    private static int SPLASH_SCREEN = 4000;
    List<String> loc = new ArrayList<>();
    public static List<LatLng> locCor = new ArrayList<>();
    ArrayList<String> coordinatess = new ArrayList<>();
    Animation topAnim, bottomAnim;
    ImageView globe, thehelpyoudeserve, citizenaidsplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);
        getCoordinates();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        globe = findViewById(R.id.globe);
        thehelpyoudeserve = findViewById(R.id.thehelpyoudeserve);
        citizenaidsplash = findViewById(R.id.citizenaidsplash);

        globe.setAnimation(bottomAnim);
        thehelpyoudeserve.setAnimation(bottomAnim);
        citizenaidsplash.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(splashIntent);
                finish();
            }
        },SPLASH_SCREEN);



    }
    private void getCoordinates(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GETCOOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("CONNECTED");
                            Log.e("anyText",response);
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.optJSONArray("getcoor");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.optJSONObject(i);
                                    String temp = object.getString("coordinate").trim();
                                    coordinatess.add(temp);
                                    //temp = a single coordinate
                                    //pls add to a ArrayList <String>
                                    loc.add(temp);
                                    for(String s : loc){
                                        int start = s.indexOf('(');
                                        int end = s.indexOf(')');
                                        String temporary =  s.substring(start + 1, end);
                                        int comma = temporary.indexOf(',');
                                        String tempLat = temporary.substring(0, comma);
                                        String tempLng = temporary.substring(comma + 1, temporary.length());
                                        double lat = Double.valueOf(tempLat);
                                        double lng = Double.valueOf(tempLng);
                                        locCor.add(new LatLng(lat, lng));

                                        System.out.println(lat + " " + lng);
                                    }
                                    //System.out.println("loc: " + loc.size());
                                    System.out.println("co " + temp);
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
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
