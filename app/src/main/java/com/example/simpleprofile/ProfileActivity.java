package com.example.simpleprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simpleprofile.Models.Profile;

public class ProfileActivity extends AppCompatActivity {
    private int progressResult = 0;

    private ProgressBar progressBar;
    private TextView progressResultTW;
    private Profile profile;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 10000;
    private TextView nameTW, cityTW, cityLabelTW, ageLabelTW, ageTW, hobbiesLabelTW, hobbiesTW, hobbiesTimerTW;

    private void startHobbiesTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliSeconds = millisUntilFinished;
                hobbiesTimerTW.setText(Integer.toString((int) (timeLeftInMilliSeconds/1000)));
            }
            @Override
            public void onFinish() {
                loadHobbiesInfo(true);
            }
        }.start();
    }
    private void resetHobbiesTimer(){
        this.timeLeftInMilliSeconds = 10000;
    }

    private void loadHobbiesInfo(boolean active){
        if(active){
            hobbiesLabelTW.setVisibility(View.VISIBLE);
            hobbiesTW.setVisibility(View.VISIBLE);
            String hobbies = "";
            for(String hobby : profile.getHobbies()){
                hobbies += hobby + "\n";
            }
            hobbiesTW.setText(hobbies.substring(0,hobbies.length() - 1));
        }else{
            hobbiesLabelTW.setVisibility(View.GONE);
            hobbiesTW.setVisibility(View.GONE);
        }
    }

    private void loadProfileInfo(boolean active){
        nameTW.setText(profile.getFirstname() + " " + profile.getLastname());
        if(active){
            cityTW.setVisibility(View.VISIBLE);
            cityLabelTW.setVisibility(View.VISIBLE);
            cityTW.setText(profile.getCity().toString());

            ageLabelTW.setVisibility(View.VISIBLE);
            ageTW.setVisibility(View.VISIBLE);
            ageTW.setText(Integer.toString(profile.getAge()));

            hobbiesTimerTW.setVisibility(View.VISIBLE);
            startHobbiesTimer();
        }else{
            cityTW.setVisibility(View.GONE);
            cityLabelTW.setVisibility(View.GONE);
            ageLabelTW.setVisibility(View.GONE);
            ageTW.setVisibility(View.GONE);
            hobbiesTimerTW.setVisibility(View.GONE);
            resetHobbiesTimer();
        }
    }

    private void bindViews(){
        nameTW = findViewById(R.id.nameTW);
        cityTW = findViewById(R.id.cityTW);
        cityLabelTW = findViewById(R.id.cityLabelTW);
        ageLabelTW = findViewById(R.id.ageLabelTW);
        ageTW = findViewById(R.id.ageTW);
        hobbiesLabelTW = findViewById(R.id.hobbiesLabelTW);
        hobbiesTW = findViewById(R.id.hobbiesTW);
        progressResultTW = findViewById(R.id.progressResultTW);
        progressBar = findViewById(R.id.progressBar);
        hobbiesTimerTW = findViewById(R.id.hobbiesTimerTW);
    }

    private void startProgessBar(){
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 100; i++){
                    progressResult = i;
                    progressBar.setProgress(progressResult);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressResultTW.setText(Integer.toString(progressResult) + "%");
                        }
                    });
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadProfileInfo(true);
                    }
                });
            }
        });
        th1.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("Profile");
        bindViews();
        progressBar.setProgress(progressResult);
        progressResultTW.setText(Integer.toString(progressResult) + "%");
        loadProfileInfo(false);
        loadHobbiesInfo(false);
        startProgessBar();

    }
    
}