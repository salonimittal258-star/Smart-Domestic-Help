package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserSignupActivity extends Activity {

    EditText etUserName, etUserEmail, etUserPhone, etUserPassword;
    Button btnUserSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        etUserName = findViewById(R.id.etUserName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etUserPhone = findViewById(R.id.etUserPhone);
        etUserPassword = findViewById(R.id.etUserPassword);

        btnUserSignup = findViewById(R.id.btnUserSignup);

        btnUserSignup.setOnClickListener(v -> {

            String name = etUserName.getText().toString().trim();
            String email = etUserEmail.getText().toString().trim();
            String phone = etUserPhone.getText().toString().trim();
            String password = etUserPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()
                    || phone.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                        UserSignupActivity.this,
                        "Please fill all details",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            SharedPreferences preferences =
                    getSharedPreferences("UserData", MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("phone", phone);
            editor.putString("password", password);

            editor.apply();

            Toast.makeText(
                    UserSignupActivity.this,
                    "User account created successfully!",
                    Toast.LENGTH_LONG
            ).show();

            finish();
        });
    }
}