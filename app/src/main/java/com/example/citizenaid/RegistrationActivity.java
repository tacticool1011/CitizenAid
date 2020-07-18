package com.example.citizenaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    Button mLogin, mRegister;
    RadioButton mCitizen, mInstitution;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mLogin = findViewById(R.id.login);
        mCitizen = findViewById(R.id.citizen);
        mInstitution = findViewById(R.id.institution);

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
    }
}
