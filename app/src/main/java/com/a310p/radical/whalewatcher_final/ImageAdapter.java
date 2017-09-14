package com.a310p.radical.whalewatcher_final;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a310p.radical.whalewatcher_final.Models.WhaleImage;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by zhangzeyao on 11/9/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private ArrayList<WhaleImage> whaleImageArrayList;

    private Context context;

    public ImageAdapter(ArrayList<WhaleImage> whaleImageArrayList, Context context) {
        this.whaleImageArrayList = whaleImageArrayList;
        this.context = context;
    }

    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View imageView = inflater.inflate(R.layout.image_item,parent,false);
        ImageAdapter.ImageViewHolder viewHolder = new ImageAdapter.ImageViewHolder(imageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
            WhaleImage whaleImage = whaleImageArrayList.get(position);
            ImageView imageView = holder.imageView;
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(whaleImage.getTitle());
        Glide.with(context).using(new FirebaseImageLoader()).load(storageRef).into(imageView);
    }


    @Override
    public int getItemCount() {
        return whaleImageArrayList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageView;

        public ImageViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                WhaleImage whaleImage = whaleImageArrayList.get(position);
                Intent newIntent = new Intent(context,WhaleImageActivity.class);
                newIntent.putExtra("whaleImage",whaleImage);
                context.startActivity(newIntent);
            }
        }
    }
}
