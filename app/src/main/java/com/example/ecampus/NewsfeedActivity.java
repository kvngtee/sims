package com.example.ecampus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularimageview.CircularImageView;

public class NewsfeedActivity extends AppCompatActivity {

    CircularImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        profile = findViewById(R.id.userpic);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NewsfeedActivity.this, ProfileActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);
            }
        });


    }
}
