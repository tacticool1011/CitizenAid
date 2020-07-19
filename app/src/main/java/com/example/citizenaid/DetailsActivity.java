package com.example.citizenaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.citizenaid.MapsActivity.name1;
import static com.example.citizenaid.MapsActivity.desc1;
import static com.example.citizenaid.MapsActivity.toDelete;
import static com.example.citizenaid.MapsActivity.type1;
import static com.example.citizenaid.MapsActivity.marker1;
import static com.example.citizenaid.MapsActivity.notNull;
import static com.example.citizenaid.MapsActivity.removeMarkers;
public class DetailsActivity extends AppCompatActivity {
    private TextView name, desc, type, image;
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
        removelocation = findViewById(R.id.removelocation);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                finish();
                return;
            }
        });

        removelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDelete.add(marker1);
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                removeMarkers();
                finish();
                return;
            }
        });
        name.setText(name1);
        desc.setText(desc1);
        type.setText(type1);

    }
}
