package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a310p.radical.whalewatcher_final.Models.Whale;

import org.w3c.dom.Text;

import java.io.InputStream;

public class WhaleInformationActivity extends AppCompatActivity {

    private Whale whale;
    private ImageView whalePic;
    private TextView nameText;
    private TextView lengthText;
    private TextView weightText;
    private TextView siteText;
    private TextView monthText;
    private TextView colorText;
    private TextView descripText;
    private TextView habitatText;
    private TextView scNameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whale_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.whaleInformationToolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        Intent whaleIntent = getIntent();
        whale = whaleIntent.getParcelableExtra("whale");

        whalePic = (ImageView)findViewById(R.id.whalePic);
        nameText = (TextView)findViewById(R.id.nameText);
        lengthText = (TextView)findViewById(R.id.lengthText);
        weightText = (TextView)findViewById(R.id.weightText);
        siteText = (TextView)findViewById(R.id.siteText);
        monthText = (TextView)findViewById(R.id.monthText);
        colorText = (TextView)findViewById(R.id.colorText);
        descripText = (TextView)findViewById(R.id.descripText);
        habitatText = (TextView)findViewById(R.id.habitatText);
        scNameText = (TextView)findViewById(R.id.scNameText);


        new DownloadImageTask(whalePic).execute(whale.getUrl());
        nameText.setText(whale.getName());
        lengthText.setText(String.valueOf(whale.getLength()) + " meters");
        weightText.setText(String.valueOf(whale.getWeight()) + " KG");
        siteText.setText(whale.getPossibleSite());
        monthText.setText(whale.getPossibleMonth());
        colorText.setText(whale.getFeature());
        descripText.setText(whale.getDescription());
        habitatText.setText(whale.getHabitat());
        scNameText.setText(whale.getScientificName());
    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
