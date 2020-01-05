package com.example.ecampus.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecampus.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView) {

        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }


    public void setDetails(Context ctx, String title, String desc, String image, String date) {

        TextView mtitletv = mView.findViewById(R.id.news_title);
        TextView medesctv = mView.findViewById(R.id.news_desc);
        TextView mdatetv = mView.findViewById(R.id.dateposted);
        ImageView mimagetv = mView.findViewById(R.id.news_image);

        mtitletv.setText(title);
        medesctv.setText(desc);
        mdatetv.setText(date);
        Picasso.get().load(image).into(mimagetv);
    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }

}
