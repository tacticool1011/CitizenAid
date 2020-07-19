package com.example.citizenaid;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.citizenaid.MapsActivity.name1;
import static com.example.citizenaid.MapsActivity.desc1;
import static com.example.citizenaid.MapsActivity.toDelete;
import static com.example.citizenaid.MapsActivity.type1;
import static com.example.citizenaid.MapsActivity.marker1;
import static com.example.citizenaid.MapsActivity.notNull;
public class DetailsActivity extends AppCompatActivity {
    private TextView name, desc, type, image;

    private static String URL_GETPROFILE = "http://2fe49e011188.ngrok.io/citizenAid/getprofile.php";

    private Button back, removelocation;
    public static boolean removed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name = findViewById(R.id.institutionName);
        desc = findViewById(R.id.institutionDetails);
        type = findViewById(R.id.institutionType);
        image = findViewById(R.id.textView2);
        back = findViewById(R.id.detailsBackButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                finish();
                return;
            }
        });

        name.setText(name1);
        desc.setText(desc1);
        type.setText(type1);

    }

    private void getProfile(final String email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GETPROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.optJSONArray("gettext");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.optJSONObject(i);
                                    String temp  = object.getString("textmessage").trim();
//                                    user.addText(temp);
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
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
