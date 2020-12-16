package com.example.simpleprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView spScLogo;
    private TextView spScHeaderText;
    private TextView spScFooterText;
    private static int spScTime = 7000;

    private void bindViews(){
        spScFooterText = findViewById(R.id.spScFooterText);
        spScHeaderText = findViewById(R.id.spScHeaderText);
        spScLogo = findViewById(R.id.spScLogo);
    }

    private void doAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splashscreen_animation);
        spScHeaderText.startAnimation(animation);
        spScFooterText.startAnimation(animation);
        spScLogo.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,InformationsActivity.class);
                startActivity(intent);
                finish();
            }
        },spScTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        doAnimation();

    }
}