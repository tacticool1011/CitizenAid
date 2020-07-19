package com.example.citizenaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.citizenaid.Users.Institution;
import com.example.citizenaid.Users.Institutions;

//import static com.example.citizenaid.MapsActivity.institute;
import static com.example.citizenaid.LoginActivity.institutions;
import static com.example.citizenaid.MapsActivity.addedanything;
//import static com.example.citizenaid.MapsActivity.institute;

public class InstitutionActivity extends AppCompatActivity {
    private EditText name, description, type;
    private Button confirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution);

        name = findViewById(R.id.institutionname);
        type = findViewById(R.id.type);
        description = findViewById(R.id.description);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInstituion();
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                finish();
                return;
            }
        });
    }
    public void createInstituion(){
        Institution created = new Institution(institutions , MapsActivity.getClickPos(), description.getText().toString(), name.getText().toString(), type.getText().toString());
        institutions.addLocations(created);

        addedanything = true;
    }
}
