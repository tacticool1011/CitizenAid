package com.example.citizenaid;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.citizenaid.Users.Citizen;
import com.example.citizenaid.Users.Institutions;

public class LoginActivity extends AppCompatActivity {

    Button mCheat, mRegister, mLogin;
    public  static Citizen citizen;
    public static Institutions institutions;
    EditText mEmail, mPassword;
    private static String URL_LOGIN = "http://16f8aaaf4bdf.ngrok.io/citizenAid/login.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRegister = findViewById(R.id.register);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.login);

        //to register page
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                finish();
                return;
            }
        });

        //runs php code and if successful, log user in
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                Login(email, password);
            }
        });
    }

    private void Login(final String email, final String password){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    //looks in database and i make strings storing the values that we get
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String isCitizen = object.getString("isCitizen").trim();
                                    int ID = object.getInt("id");

                                    //make instances of Citizen and Institutions, the one that is not would be set to random string
                                    if (isCitizen.equals("true")){
                                        citizen = new Citizen (name, email);
                                        institutions = new Institutions("notinstitutions", "notinstitutions");
                                    } else{
                                        institutions = new Institutions(name, email);
                                        citizen = new Citizen ("notcitizen", "notcitizen");
                                        System.out.println(getCitizen().getEmail());
                                    }

                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                                }
                            } else{
                                mEmail.setText("");
                                mPassword.setText("");
                                Toast.makeText(LoginActivity.this, "Username or Password incorrect \n Please Try Again", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mLogin.setVisibility(View.VISIBLE);
                            mEmail.setText("");
                            mPassword.setText("");
                            Toast.makeText(LoginActivity.this, "Username or Password incorrect \n Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public static Citizen getCitizen() {
        return citizen;
    }

    public static Institutions getInstitutions() {
        return institutions;
    }
}
