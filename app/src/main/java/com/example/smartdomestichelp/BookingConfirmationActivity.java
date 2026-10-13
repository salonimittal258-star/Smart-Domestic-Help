package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BookingConfirmationActivity extends Activity {

    TextView tvWorker;
    TextView tvService;
    TextView tvLocality;
    TextView tvDate;
    TextView tvTime;

    Button btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        String worker = getIntent().getStringExtra("worker");
        String service = getIntent().getStringExtra("service");
        String locality = getIntent().getStringExtra("locality");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");

        tvWorker = findViewById(R.id.tvWorker);
        tvService = findViewById(R.id.tvService);
        tvLocality = findViewById(R.id.tvLocality);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);

        btnBackHome = findViewById(R.id.btnBackHome);

        tvWorker.setText("👤 Worker: " + worker);
        tvService.setText("🛠 Service: " + service);
        tvLocality.setText("📍 Location: " + locality);
        tvDate.setText("📅 Date: " + date);
        tvTime.setText("⏰ Time: " + time);

        btnBackHome.setOnClickListener(v -> {

            Intent intent = new Intent(
                    BookingConfirmationActivity.this,
                    UserHomeActivity.class
            );

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(intent);

            finish();
        });
    }
}

