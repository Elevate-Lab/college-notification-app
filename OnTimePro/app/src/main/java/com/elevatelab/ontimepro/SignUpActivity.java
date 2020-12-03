package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {
    private TextView sign_in;
    private Button sign_up;
    private TextInputEditText name, mob_no, email, pass, cnf_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    private void initViews(){
        sign_in = findViewById(R.id.signUp_txt);
        sign_up = findViewById(R.id.sign_up_btn);
        name = findViewById(R.id.name_edit_text);
        mob_no = findViewById(R.id.mob_edit_text);
        email = findViewById(R.id.email_edit_text);
        pass = findViewById(R.id.password_edit_text);
        cnf_pass = findViewById(R.id.cnf_pass_edit_text);
    }
}
