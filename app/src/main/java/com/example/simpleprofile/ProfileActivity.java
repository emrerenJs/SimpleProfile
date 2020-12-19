package com.example.simpleprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.simpleprofile.Models.Profile;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Profile profile = (Profile) intent.getSerializableExtra("Profile");
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
}