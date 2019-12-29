package com.example.ecampus;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends AppCompatActivity {

    private EditText StudentID, Password;
    private TextView Fname, forgotpass;
    private Button button;
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
        userpic = findViewById(R.id.userpic);


        Fname.setVisibility(View.GONE);
        Password.setVisibility(View.GONE);
        button.setText("Next");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Query Firestore and retrieve the document
                Source source = Source.SERVER;
                db.collection("students")
                        .whereEqualTo("student_ID", StudentID.getText().toString())
                        .get(source)
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    QuerySnapshot snapshot = task.getResult();
                                    if (snapshot.getDocuments().size() == 1) {
                                        Fname.setVisibility(View.VISIBLE);
                                        Fname.setText(snapshot.getDocuments().get(0).get("fname").toString() + ".");
                                        Picasso.get().load(snapshot.getDocuments().get(0).get("image").toString()).placeholder(R.drawable.user).into(userpic);

                                        // slide-down animation
                                        Animation slidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                                        if (Password.getVisibility() == View.GONE) {
                                            Password.setVisibility(View.VISIBLE);
                                            Password.startAnimation(slidedown);
                                        }
                                        button.setText("Login");


                                        Log.d("GET_DATA", "DocumentSnapshot data: " + task.getResult().getDocuments());
                                        Intent intent = new Intent(LoginActivity.this, HomescreenActivity.class);
                                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                        startActivity(intent);
                                        Toasty.success(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT, true).show();

                                    } else {
                                        Log.d("GET_DATA", "No such document");
                                        Toasty.error(getApplicationContext(), "Invalid Student ID...please try again").show();
                                    }
                                } else {
                                    Toasty.error(getApplicationContext(), "Couldn't connect to the internet").show();
                                    Log.d("GET_DATA", "get failed with ", task.getException());
                                }
                            }
                        });

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
