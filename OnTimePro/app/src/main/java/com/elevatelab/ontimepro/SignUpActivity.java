package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.elevatelab.ontimepro.adapters.SignUpFragmentStateAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SignUpActivity extends AppCompatActivity {
    private TextView sign_in;
    private ViewPager2 signUpViewPager;
    private TabLayout signUpTabLayout;

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

        signUpViewPager.setAdapter(new SignUpFragmentStateAdapter(SignUpActivity.this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                signUpTabLayout, signUpViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Organization");
                } else {
                    tab.setText("Individual");
                }
            }
        }
        );
        tabLayoutMediator.attach();

    }
    private void initViews() {
        sign_in = findViewById(R.id.signUp_txt);
        signUpViewPager = findViewById(R.id.sign_up_viewPager);
        signUpTabLayout = findViewById(R.id.sign_up_tab_layout);
    }
}
