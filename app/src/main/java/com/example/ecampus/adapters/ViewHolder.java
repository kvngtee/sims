package com.example.ecampus.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecampus.R;
import com.example.ecampus.models.Blog;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.news_title)
    TextView Title;

    @BindView(R.id.news_desc)
    TextView Desc;

    @BindView(R.id.dateposted)
    TextView NewsDate;

    @BindView(R.id.news_image)
    ImageView NewsImage;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Context ctx, Blog blog) {
        Title.setText(blog.getTitle());
        Desc.setText(blog.getDesc());
        NewsDate.setText(blog.getDate().toString());
        Picasso.get().load(blog.getImage()).placeholder(R.drawable.logo).into(NewsImage);
    }
}
