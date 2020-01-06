package com.example.ecampus.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ecampus.R;

public class SettingsActivity extends AppCompatActivity {

    ToggleButton notificationtogglebtn, darkmodetogglebtn;
    private CardView cardview;
    private LinearLayout agreement, changepassword, appfeedback, bugreport, rate, developer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        cardview = findViewById(R.id.cardview);
        notificationtogglebtn = findViewById(R.id.notificationtoggleButton);
        darkmodetogglebtn = findViewById(R.id.darkmodetoggleButton);
        agreement = findViewById(R.id.agreements);
        changepassword = findViewById(R.id.changepassword);
        appfeedback = findViewById(R.id.app_feedback);
        bugreport = findViewById(R.id.bug);
        rate = findViewById(R.id.rate);
        developer = findViewById(R.id.developer);


        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardview.setCardBackgroundColor(Color.parseColor("#6C63Ff"));
                Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);
            }
        });


        notificationtogglebtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {

            }
        });

        darkmodetogglebtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {

            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepassword.setBackgroundColor(Color.parseColor("#252525"));
                Intent intent = new Intent(SettingsActivity.this, ForgotpasswordActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);

            }
        });

        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreement.setBackgroundColor(Color.parseColor("#252525"));
                Toast.makeText(getApplicationContext(), "feedback", Toast.LENGTH_SHORT).show();
                //dialog


            }
        });

        appfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appfeedback.setBackgroundColor(Color.parseColor("#252525"));
                Intent emailintent = new Intent(Intent.ACTION_SEND);
                emailintent.setType("message/rfc822");
                emailintent.putExtra(Intent.EXTRA_EMAIL, new String[]{"asieduprince123@gmail.com"});
                emailintent.putExtra(Intent.EXTRA_SUBJECT, "FEEDBACK");
                emailintent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(emailintent, "Send Email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SettingsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bugreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailintent = new Intent(Intent.ACTION_SEND);
                emailintent.setType("message/rfc822");
                emailintent.putExtra(Intent.EXTRA_EMAIL, new String[]{"asieduprince123@gmail.com"});
                emailintent.putExtra(Intent.EXTRA_SUBJECT, "BUG REPORT");
                emailintent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(emailintent, "Send Email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SettingsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate.setBackgroundColor(Color.parseColor("#252525"));
                Toast.makeText(getApplicationContext(), "rate", Toast.LENGTH_SHORT).show();

            }
        });

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                developer.setBackgroundColor(Color.parseColor("#252525"));
                Intent intent = new Intent(SettingsActivity.this, DeveloperActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);


            }
        });

    }
}
