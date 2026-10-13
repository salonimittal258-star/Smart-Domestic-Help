package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class PredictionActivity extends Activity {

    TextView tvService;
    TextView tvLocality;
    TextView tvDetails;
    TextView tvPrice;
    Button btnViewWorkers;

    String service;
    String locality;

    String[] detailsArray;

    String serviceType = "Other";
    String quantity = "1";
    String urgency = "Normal";
    String houseSize = "2 BHK";
    String frequency = "One-time";
    String materialRequired = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prediction);

        tvService = findViewById(R.id.tvService);
        tvLocality = findViewById(R.id.tvLocality);
        tvDetails = findViewById(R.id.tvDetails);
        tvPrice = findViewById(R.id.tvPrice);
        btnViewWorkers = findViewById(R.id.btnViewWorkers);

        Intent intent = getIntent();

        service = intent.getStringExtra("service");
        locality = intent.getStringExtra("locality");

        if (service == null || service.trim().isEmpty()) {
            service = "Cleaning";
        }

        if (locality == null || locality.trim().isEmpty()) {
            locality = "Rajpur Road";
        }

        int totalDetails = intent.getIntExtra("total_details", 0);

        detailsArray = new String[totalDetails];

        for (int i = 0; i < totalDetails; i++) {
            detailsArray[i] = intent.getStringExtra("detail_" + i);

            if (detailsArray[i] == null) {
                detailsArray[i] = "";
            }
        }

        mapDetailsToModel();

        tvService.setText(service);
        tvLocality.setText("📍 " + locality);

        tvDetails.setText(createDetailsText(intent));

        calculatePrice();

        btnViewWorkers.setOnClickListener(v -> {

            Intent workerIntent = new Intent(
                    PredictionActivity.this,
                    WorkerListActivity.class
            );

            workerIntent.putExtra("service", service);
            workerIntent.putExtra("locality", locality);

            startActivity(workerIntent);
        });
    }

    private void calculatePrice() {

        try {

            OfflinePricePredictor predictor =
                    new OfflinePricePredictor(this);

            double predictedPrice =
                    predictor.predict(
                            service,
                            locality,
                            serviceType,
                            quantity,
                            urgency,
                            houseSize,
                            frequency,
                            materialRequired
                    );

            if (predictedPrice > 0) {

                tvPrice.setText(
                        "₹ " + String.format(
                                Locale.US,
                                "%.2f",
                                predictedPrice
                        )
                );

            } else {

                tvPrice.setText("Price unavailable");
            }

        } catch (Exception e) {

            tvPrice.setText("Price unavailable");

            Toast.makeText(
                    this,
                    "Prediction error: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void mapDetailsToModel() {

        if (service.equalsIgnoreCase("Cleaning")) {

            if (hasValue(0)) {
                serviceType = detailsArray[0];
            }

            if (hasValue(1)) {
                quantity = detailsArray[1];
            }

            if (hasValue(2)) {
                quantity = combineQuantity(
                        quantity,
                        detailsArray[2]
                );
            }

            if (hasValue(3)) {
                houseSize = detailsArray[3];
            }
        }

        else if (service.equalsIgnoreCase("Plumbing")) {

            if (hasValue(0)) {
                serviceType = detailsArray[0];
            }

            if (hasValue(1)) {
                quantity = detailsArray[1];
            }

            if (hasValue(2)) {
                materialRequired = detailsArray[2];
            }

            if (hasValue(3)) {
                urgency = detailsArray[3];
            }
        }

        else if (service.equalsIgnoreCase("Electrician")) {

            if (hasValue(0)) {
                serviceType = detailsArray[0];
            }

            if (hasValue(1)) {
                quantity = detailsArray[1];
            }

            if (hasValue(3)) {
                materialRequired = detailsArray[3];
            }

            if (hasValue(4)) {
                urgency = detailsArray[4];
            }
        }

        else if (service.equalsIgnoreCase("Cook")) {

            if (hasValue(0)) {
                quantity = detailsArray[0];
            }

            if (hasValue(2)) {
                serviceType = detailsArray[2];
            }

            if (hasValue(3)) {
                quantity = detailsArray[3];
            }

            if (hasValue(4)) {
                frequency = detailsArray[4];
            }
        }

        else if (service.equalsIgnoreCase("Maid")) {

            if (hasValue(0)) {
                serviceType = detailsArray[0];
            }

            if (hasValue(1)) {
                quantity = detailsArray[1];
            }

            if (hasValue(2)) {
                houseSize = detailsArray[2];
            }

            if (hasValue(3)) {
                frequency = detailsArray[3];
            }

            if (hasValue(4)) {
                quantity = detailsArray[4];
            }
        }

        else if (service.equalsIgnoreCase("Babysitter")) {

            if (hasValue(0)) {
                quantity = detailsArray[0];
            }

            if (hasValue(1)) {
                serviceType = detailsArray[1];
            }

            if (hasValue(2)) {
                quantity = detailsArray[2];
            }

            if (hasValue(3)) {
                serviceType = detailsArray[3];
            }
        }

        else if (service.equalsIgnoreCase("Carpenter")) {

            if (hasValue(0)) {
                serviceType = detailsArray[0];
            }

            if (hasValue(1)) {
                quantity = detailsArray[1];
            }

            if (hasValue(3)) {

                String material = detailsArray[3];

                if (material.equalsIgnoreCase("Worker")) {
                    materialRequired = "Yes";
                }
                else if (material.equalsIgnoreCase("Customer")) {
                    materialRequired = "No";
                }
                else {
                    materialRequired = "Not Sure";
                }
            }
        }
    }

    private String createDetailsText(Intent intent) {

        StringBuilder result = new StringBuilder();

        int totalDetails =
                intent.getIntExtra("total_details", 0);

        for (int i = 0; i < totalDetails; i++) {

            String question =
                    intent.getStringExtra("question_" + i);

            String value =
                    intent.getStringExtra("detail_" + i);

            if (question != null &&
                    value != null &&
                    !value.trim().isEmpty() &&
                    !value.toLowerCase().contains("select")) {

                result.append(question);
                result.append(": ");
                result.append(value);
                result.append("\n\n");
            }
        }

        String additional =
                intent.getStringExtra("details");

        if (additional != null &&
                !additional.trim().isEmpty()) {

            result.append("Additional Details: ");
            result.append(additional);
        }

        if (result.length() == 0) {
            return "No additional details provided.";
        }

        return result.toString().trim();
    }

    private boolean hasValue(int index) {

        if (detailsArray == null) {
            return false;
        }

        if (index < 0 || index >= detailsArray.length) {
            return false;
        }

        String value = detailsArray[index];

        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        return !value.toLowerCase().contains("select");
    }

    private String combineQuantity(
            String rooms,
            String bathrooms) {

        int roomCount = convertNumber(rooms);
        int bathroomCount = convertNumber(bathrooms);

        int total = roomCount + bathroomCount;

        if (total <= 1) {
            return "1";
        }

        if (total == 2) {
            return "2";
        }

        if (total == 3) {
            return "3";
        }

        return "4+";
    }

    private int convertNumber(String value) {

        if (value == null) {
            return 1;
        }

        value = value.trim();

        if (value.equals("1")) {
            return 1;
        }

        if (value.equals("2")) {
            return 2;
        }

        if (value.equals("3")) {
            return 3;
        }

        if (value.equals("4+")) {
            return 4;
        }

        if (value.equals("3+")) {
            return 3;
        }

        if (value.contains("1–2")) {
            return 2;
        }

        if (value.contains("3–4")) {
            return 4;
        }

        if (value.contains("5–6")) {
            return 6;
        }

        if (value.contains("7+")) {
            return 7;
        }

        if (value.contains("1–2 hours")) {
            return 2;
        }

        if (value.contains("3–4 hours")) {
            return 4;
        }

        if (value.contains("5–6 hours")) {
            return 6;
        }

        if (value.contains("7+ hours")) {
            return 7;
        }

        return 1;
    }
}

