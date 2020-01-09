package com.example.ecampus.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecampus.R;

import com.example.ecampus.activities.NewsfeedActivity;
import com.example.ecampus.adapters.NewsAdapter;
import com.example.ecampus.models.News;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestFragment extends Fragment {


    private View view;

    private String thisYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    private String thisMonth = DateFormat.format("MM", new Date()).toString();

    private CollectionReference News =
            FirebaseFirestore.getInstance().
                    collection("news")
                    .document(thisYear).collection(thisMonth);
    private Query query = News.orderBy("date", Query.Direction.DESCENDING);

    private FirestoreRecyclerAdapter firestoreRecyclerAdapter;

    @BindView(R.id.myrecyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;


    public LatestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() ->
        {
            refreshRecyclerView();
            swipeRefreshLayout.setRefreshing(false);
        });
    }


    public void refreshRecyclerView() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
