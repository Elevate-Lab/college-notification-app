package com.example.ontimepro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    // variables
    Animation topanim, bottomanim;
    ImageView logo;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Animations

       topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
       bottomanim= AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        logo = findViewById(R.id.imageView);
        title = findViewById(R.id.textView);

        logo.setAnimation(topanim);
        title.setAnimation(bottomanim);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                 Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                 startActivity(intent);
                 finish();

            }
        },SPLASH_SCREEN);
    }
}