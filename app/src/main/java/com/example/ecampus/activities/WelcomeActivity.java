package com.example.ecampus.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecampus.R;


public class WelcomeActivity extends AppCompatActivity {

    private Button mStartBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        mStartBtn = findViewById(R.id.startbtn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStartBtn.setBackgroundResource(R.drawable.clicked);
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }

        });

    }

}
