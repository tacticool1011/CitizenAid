package com.example.citizenaid;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.citizenaid.MapsActivity.name1;
import static com.example.citizenaid.MapsActivity.desc1;
import static com.example.citizenaid.MapsActivity.type1;
public class DetailsActivity extends AppCompatActivity {
    private TextView name, desc, type, image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name = findViewById(R.id.institutionName);
        desc = findViewById(R.id.institutionDetails);
        type = findViewById(R.id.institutionType);
        image = findViewById(R.id.textView2);

        name.setText(name1);
        desc.setText(desc1);
        type.setText(type1);

    }
}
