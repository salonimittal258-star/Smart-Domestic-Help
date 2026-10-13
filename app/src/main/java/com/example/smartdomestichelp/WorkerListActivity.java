package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkerListActivity extends Activity {

    TextView tvService;
    TextView tvLocality;
    LinearLayout workerContainer;

    String service;
    String locality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);

        service = getIntent().getStringExtra("service");
        locality = getIntent().getStringExtra("locality");

        tvService = findViewById(R.id.tvService);
        tvLocality = findViewById(R.id.tvLocality);
        workerContainer = findViewById(R.id.workerContainer);

        if (service != null) {
            tvService.setText(service);
        }

        if (locality != null) {
            tvLocality.setText("📍 " + locality);
        }

        createWorkers();
    }

    private void createWorkers() {

        workerContainer.removeAllViews();

        String[] workerNames = {
                "Aman Kumar",
                "Rahul Sharma",
                "Vikas Singh",
                "Rohit Kumar",
                "Deepak Negi",
                "Ankit Rawat",
                "Suresh Kumar",
                "Manish Thakur",
                "Arjun Singh",
                "Nitin Joshi"
        };

        String[] ratings = {
                "4.9",
                "4.8",
                "4.8",
                "4.7",
                "4.7",
                "4.6",
                "4.6",
                "4.5",
                "4.5",
                "4.4"
        };

        String[] experience = {
                "6 years",
                "5 years",
                "5 years",
                "4 years",
                "4 years",
                "4 years",
                "3 years",
                "3 years",
                "3 years",
                "2 years"
        };

        for (int i = 0; i < 10; i++) {

            // Important:
            // Create a final copy of i for the button click
            final int workerIndex = i;

            LinearLayout card = new LinearLayout(this);

            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(20, 18, 20, 18);

            LinearLayout.LayoutParams cardParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

            cardParams.setMargins(0, 0, 0, 16);

            card.setLayoutParams(cardParams);
            card.setBackgroundColor(Color.WHITE);

            // Worker Name

            TextView name = new TextView(this);

            name.setText("👤 " + workerNames[workerIndex]);
            name.setTextSize(19);
            name.setTextColor(Color.rgb(23, 37, 84));
            name.setGravity(Gravity.CENTER_VERTICAL);
            name.setTypeface(null, android.graphics.Typeface.BOLD);

            card.addView(name);

            // Service and Location

            TextView serviceText = new TextView(this);

            serviceText.setText(
                    "🛠 Service: " + service +
                            "\n📍 Location: " + locality
            );

            serviceText.setTextSize(15);
            serviceText.setTextColor(Color.DKGRAY);
            serviceText.setPadding(0, 10, 0, 0);

            card.addView(serviceText);

            // Rating and Experience

            TextView rating = new TextView(this);

            rating.setText(
                    "⭐ Rating: " + ratings[workerIndex] +
                            "     🛠 Experience: " + experience[workerIndex]
            );

            rating.setTextSize(15);
            rating.setTextColor(Color.rgb(70, 70, 70));
            rating.setPadding(0, 10, 0, 0);

            card.addView(rating);

            // Availability

            TextView availability = new TextView(this);

            availability.setText("● Available");
            availability.setTextSize(14);
            availability.setTextColor(Color.rgb(0, 137, 123));
            availability.setPadding(0, 8, 0, 8);

            card.addView(availability);

            // Book Button

            Button bookButton = new Button(this);

            bookButton.setText("Book This Worker");
            bookButton.setTextSize(15);
            bookButton.setTextColor(Color.WHITE);

            bookButton.setBackgroundTintList(
                    ColorStateList.valueOf(
                            Color.rgb(83, 109, 254)
                    )
            );

            bookButton.setOnClickListener(v -> {

                Intent intent = new Intent(
                        WorkerListActivity.this,
                        BookingActivity.class
                );

                intent.putExtra("service", service);
                intent.putExtra("locality", locality);
                intent.putExtra(
                        "worker",
                        workerNames[workerIndex]
                );

                startActivity(intent);
            });

            card.addView(bookButton);

            workerContainer.addView(card);
        }
    }
}