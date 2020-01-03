package com.example.ecampus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ChangepasswordActivity extends AppCompatActivity {

    private EditText newpass, confirmpass;
    private Button btnReset;
    private TextView tvback;
    private CardView cardview;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        cardview = findViewById(R.id.cardview);


        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardview.setCardBackgroundColor(Color.parseColor("#6C63Ff"));
                Intent intent = new Intent(ChangepasswordActivity.this, LoginActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);
            }
        });


        newpass = findViewById(R.id.new_password);
        confirmpass = findViewById(R.id.confirm_password);
        btnReset = findViewById(R.id.resetpassword);
        tvback = findViewById(R.id.goback);
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);


        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangepasswordActivity.this, LoginActivity.class));
                finish();
            }
        });



        }

}
