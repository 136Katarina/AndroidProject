package com.example.android.newapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

        public void onStartButtonCLicked(View button) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

