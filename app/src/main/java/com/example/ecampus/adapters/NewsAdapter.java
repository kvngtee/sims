package com.example.ecampus.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecampus.R;
import com.example.ecampus.models.Blog;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private List<Blog> mList;

    public NewsAdapter(List<Blog> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Blog blog = mList.get(position);
        Log.i("DATA", blog.toString());
        holder.newsTitle.setText(blog.getTitle());
        holder.newsDesc.setText(blog.getDesc());
        holder.newsDate.setReferenceTime(blog.getDate().getTime());
        Picasso.get().load(blog.getImage()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsDesc;
        ImageView newsImage;
        RelativeTimeTextView newsDate;

        public MyViewHolder(View view) {
            super(view);
            newsTitle = view.findViewById(R.id.news_title);
            newsDesc = view.findViewById(R.id.news_desc);
            newsDate = view.findViewById(R.id.dateposted);
            newsImage = view.findViewById(R.id.news_image);

        }
    }


}


/*

 */