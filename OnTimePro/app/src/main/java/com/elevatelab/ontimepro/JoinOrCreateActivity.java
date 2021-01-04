package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class JoinOrCreateActivity extends AppCompatActivity {


    private Button joinChannelOption;
    private Button createChannelOption;
    private Intent receiver;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_or_create);

        receiver = getIntent();
        final String instiCode = receiver.getStringExtra("instiCode");
        joinChannelOption = findViewById(R.id.joinChannelOption);
        createChannelOption = findViewById(R.id.createChannelOption);
        auth = FirebaseAuth.getInstance();

        joinChannelOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JoinOrCreateActivity.this, Join_channel.class));
            }
        });

        createChannelOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth.getUid().equals(instiCode))
                {
                    Intent intent = new Intent(JoinOrCreateActivity.this,CreateChannelActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(JoinOrCreateActivity.this, "Only For Admins", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}