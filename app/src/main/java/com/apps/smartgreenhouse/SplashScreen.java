package com.apps.smartgreenhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);  //Sleeping for 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Going to Main Activity
                Intent goToMainActivity = new Intent(SplashScreen.this, DrawerActivity.class);
                startActivity(goToMainActivity);
                finish();
            }
        }).start();
    }
}