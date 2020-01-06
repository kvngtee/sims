package com.example.ecampus.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ecampus.R;
import com.example.ecampus.activities.NewsfeedActivity;
import com.example.ecampus.adapters.NewsAdapter;
import com.example.ecampus.models.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class YesterdayFragment extends Fragment {

    private List<Blog> mList = new ArrayList<>();
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public YesterdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        mList = ((NewsfeedActivity) getActivity()).getNewsList();

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        recyclerView = view.findViewById(R.id.myrecyclerview);
        NewsAdapter newsAdapter = new NewsAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(newsAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList = ((NewsfeedActivity) getActivity()).getNewsList();
                if (mList != null || mList.size() < 1) {
                    // show no news logo/icon
                } else {
                    refreshRecyclerView();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        return view;
    }

    private void refreshRecyclerView() {
        NewsAdapter newsAdapter = new NewsAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(newsAdapter);
    }


}
