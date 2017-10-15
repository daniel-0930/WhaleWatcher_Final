package com.a310p.radical.whalewatcher_final;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.twitter.sdk.android.core.models.Tweet;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Status;

/**
 * Created by zhangzeyao on 24/9/17.
 */

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.TweetViewHolder> {

    private static final String LOGIN_FORMAT = "@%s";
    private static final String DATE_TIME_PATTERN = "dd MMM";
    private final Context context;
    private final List<Tweet> tweets;


    public class TweetViewHolder extends RecyclerView.ViewHolder{
        public CardView cardTweet;
        public ImageView tweetAvater;
        public TextView tweetName;
        public TextView tweetLogin;
        public TextView tweetDate;
        public TextView tweetMessage;

        public TweetViewHolder(View itemView){
            super(itemView);
            cardTweet = (CardView)itemView.findViewById(R.id.cardTweet);
            tweetName = (TextView)itemView.findViewById(R.id.tweet_name);
            tweetAvater = (ImageView) itemView.findViewById(R.id.tweet_avatar);
            tweetLogin = (TextView)itemView.findViewById(R.id.tweet_login);
            tweetDate = (TextView)itemView.findViewById(R.id.tweet_date);
            tweetMessage = (TextView)itemView.findViewById(R.id.tweet_message);

        }
    }

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    @Override
    public TweetsAdapter.TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tweets,parent,false);
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetsAdapter.TweetViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        Glide.with(context).load(tweet.user.profileImageUrl).into(holder.tweetAvater);
        holder.tweetName.setText(tweet.user.name);
        holder.tweetLogin.setText(String.format(LOGIN_FORMAT,tweet.user.screenName));
        holder.tweetDate.setText(tweet.createdAt.substring(0,10));

        holder.tweetMessage.setText(tweet.text);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }
}
