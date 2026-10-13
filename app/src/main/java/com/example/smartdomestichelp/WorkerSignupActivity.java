package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WorkerSignupActivity extends Activity {

        EditText etWorkerName, etWorkerEmail, etWorkerPhone;
        EditText etWorkerPassword, etWorkerService;
        EditText etWorkerLocation, etWorkerExperience;

        Button btnWorkerSignup;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_worker_signup);

                etWorkerName = findViewById(R.id.etWorkerName);
                etWorkerEmail = findViewById(R.id.etWorkerEmail);
                etWorkerPhone = findViewById(R.id.etWorkerPhone);
                etWorkerPassword = findViewById(R.id.etWorkerPassword);
                etWorkerService = findViewById(R.id.etWorkerService);
                etWorkerLocation = findViewById(R.id.etWorkerLocation);
                etWorkerExperience = findViewById(R.id.etWorkerExperience);

                btnWorkerSignup = findViewById(R.id.btnWorkerSignup);

                btnWorkerSignup.setOnClickListener(v -> {

                        String name = etWorkerName.getText().toString().trim();
                        String email = etWorkerEmail.getText().toString().trim();
                        String phone = etWorkerPhone.getText().toString().trim();
                        String password = etWorkerPassword.getText().toString().trim();
                        String service = etWorkerService.getText().toString().trim();
                        String location = etWorkerLocation.getText().toString().trim();
                        String experience = etWorkerExperience.getText().toString().trim();

                        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()
                                || password.isEmpty() || service.isEmpty()
                                || location.isEmpty() || experience.isEmpty()) {

                                Toast.makeText(
                                        WorkerSignupActivity.this,
                                        "Please fill all details",
                                        Toast.LENGTH_SHORT
                                ).show();

                                return;
                        }

                        SharedPreferences preferences =
                                getSharedPreferences("WorkerData", MODE_PRIVATE);

                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putString("name", name);
                        editor.putString("email", email);
                        editor.putString("phone", phone);
                        editor.putString("password", password);
                        editor.putString("service", service);
                        editor.putString("location", location);
                        editor.putString("experience", experience);

                        editor.apply();

                        Toast.makeText(
                                WorkerSignupActivity.this,
                                "Worker account created successfully!",
                                Toast.LENGTH_LONG
                        ).show();

                        finish();
                });
        }
}