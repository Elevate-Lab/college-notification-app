package com.example.ontimepro;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent welcomeIntent = new Intent(WelcomeActivity.this , com.example.ontimepro.SignUpActivity.class);
        startActivity(welcomeIntent);
    }
}