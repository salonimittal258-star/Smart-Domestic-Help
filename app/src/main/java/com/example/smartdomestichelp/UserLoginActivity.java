package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLoginActivity extends Activity {

    EditText etUserEmail, etUserPassword;
    Button btnUserLogin;
    TextView tvUserSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        etUserEmail = findViewById(R.id.etUserEmail);
        etUserPassword = findViewById(R.id.etUserPassword);
        btnUserLogin = findViewById(R.id.btnUserLogin);
        tvUserSignup = findViewById(R.id.tvUserSignup);

        btnUserLogin.setOnClickListener(v -> {

            String email = etUserEmail.getText().toString().trim();
            String password = etUserPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                        UserLoginActivity.this,
                        "Please enter email and password",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            SharedPreferences preferences =
                    getSharedPreferences("UserData", MODE_PRIVATE);

            String savedEmail =
                    preferences.getString("email", "");

            String savedPassword =
                    preferences.getString("password", "");

            if (email.equals(savedEmail)
                    && password.equals(savedPassword)) {

                Toast.makeText(
                        UserLoginActivity.this,
                        "Login successful!",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(
                        UserLoginActivity.this,
                        UserHomeActivity.class
                );

                startActivity(intent);

            } else {

                Toast.makeText(
                        UserLoginActivity.this,
                        "Invalid email or password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        tvUserSignup.setOnClickListener(v -> {

            Intent intent = new Intent(
                    UserLoginActivity.this,
                    UserSignupActivity.class
            );

            startActivity(intent);
        });
    }
}
