package com.example.ecampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecampus.R;
import com.example.ecampus.adapters.NewsAdapter;
import com.example.ecampus.models.Blog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsfeedActivity extends AppCompatActivity {

    CircularImageView profile;

    FirebaseFirestore db;
    RecyclerView recyclerView;

    TabLayout tabLayout;

    List<Blog> latestList = new ArrayList();
    List<Blog> yesterdaysList = new ArrayList();
    List<Blog> lastweeksList = new ArrayList();
    List<Blog> olderList = new ArrayList();
    List<Blog> mList = new ArrayList();

    String year, month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        profile = findViewById(R.id.userpic);
        tabLayout = findViewById(R.id.tablayout);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsfeedActivity.this, ProfileActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intent);
            }
        });


        db = FirebaseFirestore.getInstance();
        db.collection("news")
                .document("2020").collection("01").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("TAG", "listen:error", e);
                    return;
                }
                mList = new ArrayList();
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

                    switch (doc.getType()) {
                        case ADDED:
                            if (daysDiff <= 0) {
                                Log.i("TODAY", "It's today");
                                latestList.add(newBlog);
                            } else if (daysDiff == 1) {
                                Log.i("YESTERDAY", "It's was yesterday");
                                yesterdaysList.add(newBlog);
                            } else if (daysDiff > 1 && daysDiff <= 7) {
                                Log.i("Last Week", "This was Last Week");
                                lastweeksList.add(newBlog);
                            } else {
                                Log.i("OLDER", "This is too Old");
                                olderList.add(newBlog);
                            }
                            break;
                        case MODIFIED:
                            Log.d("TAG", "Modified Msg: " + doc.getDocument().toObject(Message.class));
                            break;
                        case REMOVED:
                            Log.d("TAG", "Removed Msg: " + doc.getDocument().toObject(Message.class));
                            break;
                    }
                }  // After populating our list, lets create the adapters for our recyclerViews
                recyclerView = findViewById(R.id.myrecyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                refreshList(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()));

            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                refreshList(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                refreshList(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                refreshList(tab);

            }
        });

    }

    private void refreshList(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                mList = latestList;
                break;
            case 1:
                mList = yesterdaysList;
            case 2:
                mList = lastweeksList;
            case 3:
                mList = olderList;
        }
        setAdapter();
    }

    private void setAdapter() {
        NewsAdapter newsAdapter = new NewsAdapter(mList);
        recyclerView.setAdapter(newsAdapter);
    }
}
