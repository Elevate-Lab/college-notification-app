package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextView mSignUp;
    private TextInputEditText email, password;
    private Button googleLoginBtn, signInBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initViews(){
        mSignUp= findViewById(R.id.signUp_txt);
        email = findViewById(R.id.email_edit_text_login);
        password = findViewById(R.id.pass_edit_text_login);
        googleLoginBtn = findViewById(R.id.google_login_btn);
        signInBtn = findViewById(R.id.sign_in_btn);
    }
}