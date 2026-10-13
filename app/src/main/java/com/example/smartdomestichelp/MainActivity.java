package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    Button btnUserLogin, btnWorkerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUserLogin = findViewById(R.id.btnUserLogin);
        btnWorkerLogin = findViewById(R.id.btnWorkerLogin);

        btnUserLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
            startActivity(intent);
        });

        btnWorkerLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WorkerLoginActivity.class);
            startActivity(intent);
        });
    }
}