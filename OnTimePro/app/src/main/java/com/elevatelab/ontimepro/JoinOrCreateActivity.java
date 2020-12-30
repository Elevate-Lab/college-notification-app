package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class JoinOrCreateActivity extends AppCompatActivity {


    private Button joinChannelOption;
    private Button createChannelOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_or_create);

        joinChannelOption = findViewById(R.id.joinChannelOption);
        createChannelOption = findViewById(R.id.createChannelOption);

        createChannelOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinOrCreateActivity.this,CreateChannelActivity.class);
                startActivity(intent);
            }
        });


    }
}