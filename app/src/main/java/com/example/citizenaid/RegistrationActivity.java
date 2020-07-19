package com.example.citizenaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import android.widget.RadioButton;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    Button mLogin, mRegister;
    RadioButton mCitizen, mInstitution;
    EditText name, email, password, c_password;
        private static String URL_REGIST = LoginActivity.ngrokID+"/citizenAid/register.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mLogin = findViewById(R.id.login);
        mCitizen = findViewById(R.id.citizen);
      
        mInstitution = findViewById(R.id.institution);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        mRegister = findViewById(R.id.register);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        mCitizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCitizen.setChecked(true);
                mInstitution.setChecked(false);
            }
        });

        mInstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInstitution.setChecked(true);
                mCitizen.setChecked(false);
            }
        });


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checks if input is valid
                if (!password.getText().toString().trim().equals(c_password.getText().toString().trim())){
                    password.setError("Passwords do not match");
                    c_password.setError("Passwords do not match");
                    return;
                }
                if (name.getText().toString().length()<1){
                    name.setError("Please enter name");
                    return;
                }
                if (email.getText().toString().length()<1){
                    email.setError("Please enter email");
                    return;
                }
                if (password.getText().toString().length()<1){
                    password.setError("Please enter password");
                    return;
                }
                if (!mCitizen.isChecked() && !mInstitution.isChecked()){
                    Toast.makeText(RegistrationActivity.this, "Choose Organization or Citizen", Toast.LENGTH_SHORT).show();
                    return;
                }
                //creates new row in database
                Regist();
            }
        });
    }


    private void Regist(){
        mRegister.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String isCitizen;
        if (mCitizen.isChecked()){
            isCitizen = "true";
        } else {
            isCitizen = "false";
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(RegistrationActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegistrationActivity.this, "Register Error" + e.toString(), Toast.LENGTH_SHORT).show();
                            mRegister.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistrationActivity.this, "Register Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        mRegister.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put ("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("isCitizen", isCitizen);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
