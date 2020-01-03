package com.example.ecampus;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mikhaellopez.circularimageview.CircularImageView;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends AppCompatActivity {

    private EditText StudentID, Password;
    private TextView Fname, info, forgotpass;
    private Button button;
    private CardView cardview;
    private CircularImageView userpic;
    private ProgressDialog mProgressDialog;
    private int backButtonCount = 0;
    boolean doubleBackToExitPressedOnce = false;
    private FirebaseFirestore db;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();


        StudentID = findViewById(R.id.studentid);
        Password = findViewById(R.id.password);
        button = findViewById(R.id.loginbtn);
        Fname = findViewById(R.id.Fname);
        info = findViewById(R.id.info);
        userpic = findViewById(R.id.userpic);
        cardview = findViewById(R.id.cardview);


        Fname.setVisibility(View.GONE);
        StudentID.getText().toString().trim();

        StudentID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //has focus
                    if (StudentID.length() == 17) {
                        //check if user id is in fire store and display data
                        Toast.makeText(getApplicationContext(), "17", Toast.LENGTH_LONG).show();

                    } else {

                        //user id not in firestore
                        // Toast.makeText(getApplicationContext(), "Student ID not registered!!\nSee admin to get registered!", Toast.LENGTH_LONG).show();

                    }

                } else {
                    //has no focus

                }
            }
        });

        StudentID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (StudentID.length() == 17) {
                    //check if user id is in fire store and display data
                    Toast.makeText(getApplicationContext(), "17", Toast.LENGTH_LONG).show();


                } else {

                    //user id not in firestore
                    // Toast.makeText(getApplicationContext(), "Student ID not registered!!\nSee admin to get registered!", Toast.LENGTH_LONG).show();

                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundResource(R.drawable.clicked);
                Intent intent = new Intent(LoginActivity.this, HomescreenActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);
                Toasty.success(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT, true).show();

            }
        });

        forgotpass = findViewById(R.id.forgot_pass);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, ChangepasswordActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);

            }
        });


        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardview.setCardBackgroundColor(Color.parseColor("#6C63Ff"));
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Login CheckPoint", "LoginActivity resumed");
        //check Internet Connection
        // new CheckInternetConnection(this).checkConnection();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //mProgressDialog.dismiss();
        Log.e("Login CheckPoint", "LoginActivity stopped");

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void onCheckboxClicked(View view) {
    }
}
