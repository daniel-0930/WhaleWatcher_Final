package com.a310p.radical.whalewatcher_final;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a310p.radical.whalewatcher_final.Models.Whale;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by zhangzeyao on 17/9/17.
 */

public class WhaleListAdapter extends RecyclerView.Adapter<WhaleListAdapter.WhaleViewHolder> {
    private Context context;
    private ArrayList<Whale> whaleArrayList;



    public class WhaleViewHolder extends RecyclerView.ViewHolder{

        public TextView whaleNameText,whaleShortdisText;
        public ImageView whalepicView;
        public CardView cardWhale;

        public WhaleViewHolder(View view){
            super(view);
            cardWhale = (CardView)view.findViewById(R.id.cardWhale);
            whaleNameText = (TextView)view.findViewById(R.id.whaleInfoNameText);
            whaleShortdisText = (TextView)view.findViewById(R.id.whaleInfodisText);
            whalepicView = (ImageView)view.findViewById(R.id.whaleInfoImage);
        }
    }

    public WhaleListAdapter(Context context,ArrayList<Whale> whaleArrayList) {
       this.context = context;
        this.whaleArrayList = whaleArrayList;
    }

    @Override
    public WhaleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_whale,parent,false);

        return new WhaleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WhaleViewHolder holder, int position) {
        final Whale whale = whaleArrayList.get(position);
        holder.whaleNameText.setText(whale.getName());
        holder.whaleShortdisText.setText(whale.getShortDiscrip());
        Glide.with(context).load(whale.getUrl()).into(holder.whalepicView);

        holder.cardWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whaleIntent = new Intent(context,WhaleInformationActivity.class);
                whaleIntent.putExtra("whale", whale);
                context.startActivity(whaleIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return whaleArrayList.size();
    }

}
