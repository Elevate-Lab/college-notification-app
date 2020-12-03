package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button registerBtn, loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initViews();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(WelcomeActivity.this , com.elevatelab.ontimepro.SignUpActivity.class);
                startActivity(signUpIntent);
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(WelcomeActivity.this, com.elevatelab.ontimepro.LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
    private void initViews(){
        registerBtn = findViewById(R.id.login_register_btn);
        loginBtn = findViewById(R.id.login_btn);
    }
}