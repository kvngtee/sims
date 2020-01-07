package com.example.ecampus.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ecampus.R;
import com.example.ecampus.adapters.ViewPagerAdapter;
import com.example.ecampus.fragments.LastWeekFragment;
import com.example.ecampus.fragments.LatestFragment;
import com.example.ecampus.fragments.OldestFragment;
import com.example.ecampus.fragments.YesterdayFragment;
import com.example.ecampus.models.Blog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewsfeedActivity extends AppCompatActivity {

    CircularImageView profile;

    FirebaseFirestore db;

    TabLayout tabLayout;

    ViewPager viewPager;
    List<Blog> latestList = new ArrayList<>();
    List<Blog> yesterdaysList = new ArrayList<>();
    List<Blog> lastweeksList = new ArrayList<>();
    List<Blog> olderList = new ArrayList<>();
    List<Blog> mList = new ArrayList<>();

    /*   LatestFragment latestFragment;
       YesterdayFragment yesterdayFragment;
       LastWeekFragment lastWeekFragment;
       OldestFragment oldestFragment;
   */
    String thisYear, thisMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        SharedPreferences sharedPrefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);

        profile = findViewById(R.id.userpic);
        tabLayout = findViewById(R.id.tablayout);

        viewPager = findViewById(R.id.myViewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getApplicationContext(), getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        Picasso.get().load(sharedPrefs.getString("image", "")).placeholder(R.drawable.user).into(profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsfeedActivity.this, ProfileActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);
            }
        });
       /*  latestFragment = (LatestFragment)getSupportFragmentManager().findFragmentById(R.id.newsFragment);
        yesterdayFragment = (YesterdayFragment) getSupportFragmentManager().findFragmentById(R.id.newsFragment);
        lastWeekFragment = (LastWeekFragment) getSupportFragmentManager().findFragmentById(R.id.newsFragment);
        oldestFragment = (OldestFragment) getSupportFragmentManager().findFragmentById(R.id.newsFragment);
*/
        thisYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        thisMonth = DateFormat.format("MM", new Date()).toString();

        db = FirebaseFirestore.getInstance();
        db.collection("news")
                .document(thisYear).collection(thisMonth).orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("TAG", "listen:error", e);
                    return;
                }

                // Clear the List
                mList = new ArrayList<>();
                latestList = new ArrayList<>();
                yesterdaysList = new ArrayList<>();
                lastweeksList = new ArrayList<>();
                olderList = new ArrayList<>();


                // Loop through the snapshot
                for (DocumentChange doc : snapshots.getDocumentChanges()) {
                    Blog newBlog = new Blog(doc.getDocument().getId(), doc.getDocument().getString("image"),
                            doc.getDocument().getString("title"),
                            doc.getDocument().getString("desc"),
                            doc.getDocument().getDate("date"));

                    Long today = new Date().getTime();
                    Long newsDate = doc.getDocument().getDate("date").getTime();
                    Long diff = today - newsDate;
                    Log.i("DIFF", diff.toString());
                    int daysDiff = (int) (diff / (1000 * 60 * 60 * 24));
                    if (daysDiff <= 0) {
                        Log.i("TODAY", "It's today");
                        latestList.add(newBlog);
                        //latestFragment.refreshRecyclerView();
                    } else if (daysDiff == 1) {
                        Log.i("YESTERDAY", "It's was yesterday");
                        yesterdaysList.add(newBlog);
                        //    yesterdayFragment.refreshRecyclerView();
                    } else if (daysDiff > 1 && daysDiff <= 7) {
                        Log.i("Last Week", "This was Last Week");
                        lastweeksList.add(newBlog);
                        // lastWeekFragment.refreshRecyclerView();
                    } else if (daysDiff > 7) {
                        Log.i("OLDER", "This is too Old");
                        olderList.add(newBlog);
                        //  oldestFragment.refreshRecyclerView();
                    }
                    getNewsList();
                    Log.i("LATEST COUNT", String.valueOf(latestList.size()));

                    Log.i("YESTERDAY COUNT", String.valueOf(latestList.size()));
                    Log.i("LAST WEEK COUNT", String.valueOf(latestList.size()));
                    Log.i("OLDEST COUNT", String.valueOf(latestList.size()));
                }
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getNewsList();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                getNewsList();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                getNewsList();
            }
        });

    }


    public List<Blog> getNewsList() {
        switch (tabLayout.getSelectedTabPosition()) {
            case 0:
            default:
                return latestList;
            case 1:
                return yesterdaysList;
            case 2:
                return lastweeksList;
            case 3:
                return olderList;
          }
    }


}
