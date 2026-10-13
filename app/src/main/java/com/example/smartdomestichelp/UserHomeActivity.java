package com.example.smartdomestichelp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;

public class UserHomeActivity extends Activity {

    Button btnCleaning;
    Button btnPlumbing;
    Button btnElectrician;
    Button btnCook;
    Button btnMaid;
    Button btnBabysitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        // Find buttons
        btnCleaning = findViewById(R.id.btnCleaning);
        btnPlumbing = findViewById(R.id.btnPlumbing);
        btnElectrician = findViewById(R.id.btnElectrician);
        btnCook = findViewById(R.id.btnCook);
        btnMaid = findViewById(R.id.btnMaid);
        btnBabysitter = findViewById(R.id.btnBabysitter);

        // Cleaning
        btnCleaning.setOnClickListener(v ->
                openLocality("Cleaning")
        );

        // Plumbing
        btnPlumbing.setOnClickListener(v ->
                openLocality("Plumbing")
        );

        // Electrician
        btnElectrician.setOnClickListener(v ->
                openLocality("Electrician")
        );

        // Cook
        btnCook.setOnClickListener(v ->
                openLocality("Cook")
        );

        // Maid
        btnMaid.setOnClickListener(v ->
                openLocality("Maid")
        );

        // Babysitter
        btnBabysitter.setOnClickListener(v ->
                openLocality("Babysitter")
        );
    }

    private void openLocality(String service) {

        Intent intent = new Intent(
                UserHomeActivity.this,
                LocalityActivity.class
        );

        intent.putExtra("service", service);

        startActivity(intent);
    }
}