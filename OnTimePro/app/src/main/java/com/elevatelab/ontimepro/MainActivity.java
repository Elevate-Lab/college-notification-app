package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button logoutBtn;
    private NavigationView leftNavView, rightNavView;
    private FloatingActionButton channelFab;
    private ImageButton leftMenuBtn, rightMenuBtn;
    private DrawerLayout mainDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout_main);
        initViews();
        logoutBtn = findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        channelFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JoinOrCreateActivity.class);
                startActivity(intent);
            }
        });

        leftMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainDrawer.isDrawerOpen(GravityCompat.START)) {
                    mainDrawer.closeDrawer(GravityCompat.START);
                } else {
                    mainDrawer.openDrawer(GravityCompat.START);
                }
            }
        });

        rightMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainDrawer.isDrawerOpen(GravityCompat.END)) {
                    mainDrawer.closeDrawer(GravityCompat.END);
                } else {
                    mainDrawer.openDrawer(GravityCompat.END);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mainDrawer.isDrawerOpen(GravityCompat.START)) {
            mainDrawer.closeDrawer(GravityCompat.START);
        } else if (mainDrawer.isDrawerOpen(GravityCompat.END)) {
            mainDrawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    private void initViews() {
        leftNavView = findViewById(R.id.left_nav_view);
        rightNavView = findViewById(R.id.right_nav_view);
        leftMenuBtn = findViewById(R.id.left_menu_btn);
        rightMenuBtn = findViewById(R.id.right_menu_btn);
        channelFab = findViewById(R.id.channel_fab);
        mainDrawer = findViewById(R.id.drawer_main);
    }
}