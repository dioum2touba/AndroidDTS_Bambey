package com.example.dioum2touba.dtsmediacom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        //Item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        //Item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

    //set details to recycler view row
    public void setdetails(Context ctx, String title, String description, String image){
        // Views
        TextView mTitleTv = mView.findViewById(R.id.rTitleTV);
        TextView mDetailTv = mView.findViewById(R.id.rDescription);
        ImageView mImageTv = mView.findViewById(R.id.rImageView);

        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(description);
        Picasso.get().load(image).into(mImageTv);
    }

    private ViewHolder.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}
