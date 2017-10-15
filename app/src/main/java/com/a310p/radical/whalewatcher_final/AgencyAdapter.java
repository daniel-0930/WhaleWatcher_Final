package com.a310p.radical.whalewatcher_final;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a310p.radical.whalewatcher_final.Models.Agency;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by bowenz on 19/9/17.
 */

public class AgencyAdapter extends
        RecyclerView.Adapter<AgencyAdapter.AgencyViewHolder> {


        private Context context;
        private ArrayList<Agency> agencyArrayList;


        // Set up the holder of agency view

        public class AgencyViewHolder extends RecyclerView.ViewHolder{

            public TextView agencyNameText,agencyShortdisText;
            public ImageView agencypicView;
            public CardView cardagency;

            public AgencyViewHolder(View view){
                super(view);
                cardagency = (CardView)view.findViewById(R.id.cardagency);
                agencyNameText = (TextView)view.findViewById(R.id.agencyInfoNameText);
                agencyShortdisText = (TextView)view.findViewById(R.id.agencyInfodisText);
                agencypicView = (ImageView)view.findViewById(R.id.agencyInfoImage);
            }
        }

        //Constructor of agency adapter
    public AgencyAdapter(Context context,ArrayList<Agency> agencyArrayList) {
            this.context = context;
            this.agencyArrayList = agencyArrayList;
        }

        @Override
        public AgencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_agency,parent,false);

            return new AgencyViewHolder(itemView);
        }


    // Link the recycler view with its content
    @Override
        public void onBindViewHolder(AgencyViewHolder holder, int position) {
            final Agency agency = agencyArrayList.get(position);
            holder.agencyNameText.setText(agency.getAgency_name());
            holder.agencyShortdisText.setText(agency.getAgency_location());
            Glide.with(context).load(agency.getAgency_image_url()).into(holder.agencypicView);

            holder.cardagency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent agencyIntent = new Intent(context,AgencyDetail2Activity.class);
                    agencyIntent.putExtra("agency", agency.getAgency_website_url());
                    context.startActivity(agencyIntent);
                }
            });


        }


    // Provide item number
    @Override
        public int getItemCount() {
            return agencyArrayList.size();
        }

    }

