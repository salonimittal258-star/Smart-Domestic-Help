package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WorkerLoginActivity extends Activity {

    EditText etWorkerEmail, etWorkerPassword;
    Button btnWorkerLogin;
    TextView tvWorkerSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_login);

        etWorkerEmail = findViewById(R.id.etWorkerEmail);
        etWorkerPassword = findViewById(R.id.etWorkerPassword);
        btnWorkerLogin = findViewById(R.id.btnWorkerLogin);
        tvWorkerSignup = findViewById(R.id.tvWorkerSignup);

        btnWorkerLogin.setOnClickListener(v -> {

            String email =
                    etWorkerEmail.getText().toString().trim();

            String password =
                    etWorkerPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                        WorkerLoginActivity.this,
                        "Please enter email and password",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            SharedPreferences preferences =
                    getSharedPreferences("WorkerData", MODE_PRIVATE);

            String savedEmail =
                    preferences.getString("email", "");

            String savedPassword =
                    preferences.getString("password", "");

            if (email.equals(savedEmail)
                    && password.equals(savedPassword)) {

                Toast.makeText(
                        WorkerLoginActivity.this,
                        "Login successful!",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(
                        WorkerLoginActivity.this,
                        WorkerListActivity.class
                );

                startActivity(intent);

            } else {

                Toast.makeText(
                        WorkerLoginActivity.this,
                        "Invalid email or password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        tvWorkerSignup.setOnClickListener(v -> {

            Intent intent = new Intent(
                    WorkerLoginActivity.this,
                    WorkerSignupActivity.class
            );

            startActivity(intent);
        });
    }
}