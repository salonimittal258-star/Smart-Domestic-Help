package com.example.smartdomestichelp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class BookingActivity extends Activity {

    TextView tvWorker;
    TextView tvService;
    TextView tvLocality;

    EditText etDate;
    EditText etTime;
    EditText etNotes;

    Button btnConfirmBooking;

    String worker;
    String service;
    String locality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        worker = getIntent().getStringExtra("worker");
        service = getIntent().getStringExtra("service");
        locality = getIntent().getStringExtra("locality");

        tvWorker = findViewById(R.id.tvWorker);
        tvService = findViewById(R.id.tvService);
        tvLocality = findViewById(R.id.tvLocality);

        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etNotes = findViewById(R.id.etNotes);

        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        if (worker != null) {
            tvWorker.setText("👤 " + worker);
        }

        if (service != null) {
            tvService.setText("🛠 Service: " + service);
        }

        if (locality != null) {
            tvLocality.setText("📍 Location: " + locality);
        }

        etDate.setOnClickListener(v -> openDatePicker());

        etTime.setOnClickListener(v -> openTimePicker());

        btnConfirmBooking.setOnClickListener(v -> confirmBooking());
    }

    private void openDatePicker() {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(
                        this,
                        (view, selectedYear, selectedMonth, selectedDay) -> {

                            String date =
                                    selectedDay + "/" +
                                            (selectedMonth + 1) + "/" +
                                            selectedYear;

                            etDate.setText(date);
                        },
                        year,
                        month,
                        day
                );

        datePickerDialog.show();
    }

    private void openTimePicker() {

        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =
                new TimePickerDialog(
                        this,
                        (view, selectedHour, selectedMinute) -> {

                            String time =
                                    String.format(
                                            "%02d:%02d",
                                            selectedHour,
                                            selectedMinute
                                    );

                            etTime.setText(time);
                        },
                        hour,
                        minute,
                        false
                );

        timePickerDialog.show();
    }

    private void confirmBooking() {

        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String notes = etNotes.getText().toString().trim();

        if (date.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please select booking date",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (time.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please select booking time",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        Toast.makeText(
                this,
                "Booking confirmed successfully!",
                Toast.LENGTH_LONG
        ).show();
    }
}


