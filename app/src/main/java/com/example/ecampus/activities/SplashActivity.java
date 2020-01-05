package com.example.ecampus.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.ecampus.R;

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    //to get user session data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        YoYo.with(Techniques.Bounce)
                .duration(5000)
                .playOn(findViewById(R.id.logo));

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
                //overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }


    @Override
    protected void onResume() {
        super.onResume();
        }
    }
