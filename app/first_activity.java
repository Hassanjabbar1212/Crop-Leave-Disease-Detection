package com.example.cropdiseasedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class first_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first1);

        ImageButton arrowbtn;

        arrowbtn = findViewById(R.id.arrowbtn);

        arrowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(first_activity.this, MainActivity.class);
                startActivity(next);
            }
        });
    }
}