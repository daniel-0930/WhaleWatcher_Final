package com.a310p.radical.whalewatcher_final;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a310p.radical.whalewatcher_final.Models.Whale;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by zhangzeyao on 24/8/17.
 */

public class WhaleSearchAdapter extends BaseAdapter {

    private Context currentContext;
    private ArrayList<Whale> whaleList;

    public WhaleSearchAdapter(Context currentContext, ArrayList<Whale> whaleList) {
        this.currentContext = currentContext;
        this.whaleList = whaleList;
    }
    @Override
    public int getCount() {
        return whaleList.size();
    }

    @Override
    public Object getItem(int position) {
        return whaleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) currentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_whale_item, null);
        }
        ImageView whalepic = (ImageView)convertView.findViewById(R.id.whaleImage);
        TextView whaleItemName = (TextView)convertView.findViewById(R.id.whaleItemName);
        TextView whaleShortDiscrip = (TextView)convertView.findViewById(R.id.whaleShortDiscription);
        new DownloadImageTask(whalepic).execute(whaleList.get(position).getUrl());
        whaleItemName.setText(whaleList.get(position).getName());
        whaleShortDiscrip.setText(whaleList.get(position).getShortDiscrip());

        return convertView;



    }
    //Need to download the picture in the first time, this part will be modified
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

