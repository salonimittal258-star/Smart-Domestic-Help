package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LocalityActivity extends Activity {

    Button btnRajpurRoad;
    Button btnBallupur;
    Button btnPremNagar;
    Button btnClementTown;
    Button btnISBT;
    Button btnSahastradhara;
    Button btnDalanwala;
    Button btnDoiwala;
    Button btnKargiChowk;
    Button btnRaceCourse;

    TextView tvSelectedService;

    String service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locality);

        service = getIntent().getStringExtra("service");

        tvSelectedService = findViewById(R.id.tvSelectedService);

        if (service != null) {
            tvSelectedService.setText(service);
        }

        btnRajpurRoad = findViewById(R.id.btnRajpurRoad);
        btnBallupur = findViewById(R.id.btnBallupur);
        btnPremNagar = findViewById(R.id.btnPremNagar);
        btnClementTown = findViewById(R.id.btnClementTown);
        btnISBT = findViewById(R.id.btnISBT);
        btnSahastradhara = findViewById(R.id.btnSahastradhara);
        btnDalanwala = findViewById(R.id.btnDalanwala);
        btnDoiwala = findViewById(R.id.btnDoiwala);
        btnKargiChowk = findViewById(R.id.btnKargiChowk);
        btnRaceCourse = findViewById(R.id.btnRaceCourse);

        btnRajpurRoad.setOnClickListener(v -> openServiceDetails("Rajpur Road"));
        btnBallupur.setOnClickListener(v -> openServiceDetails("Ballupur"));
        btnPremNagar.setOnClickListener(v -> openServiceDetails("Prem Nagar"));
        btnClementTown.setOnClickListener(v -> openServiceDetails("Clement Town"));
        btnISBT.setOnClickListener(v -> openServiceDetails("ISBT"));
        btnSahastradhara.setOnClickListener(v -> openServiceDetails("Sahastradhara"));
        btnDalanwala.setOnClickListener(v -> openServiceDetails("Dalanwala"));
        btnDoiwala.setOnClickListener(v -> openServiceDetails("Doiwala"));
        btnKargiChowk.setOnClickListener(v -> openServiceDetails("Kargi Chowk"));
        btnRaceCourse.setOnClickListener(v -> openServiceDetails("Race Course"));
    }

    private void openServiceDetails(String locality) {

        Intent intent = new Intent(
                LocalityActivity.this,
                ServiceDetailsActivity.class
        );

        intent.putExtra("service", service);
        intent.putExtra("locality", locality);

        startActivity(intent);
    }
}