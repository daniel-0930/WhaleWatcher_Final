package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * About us activity to show the developer and data used
 * version 2.0
 * @author Zeyao Zhang and Yinhong Ren
 * @since 24/9/2017
 */
public class AboutUsActivity extends AppCompatActivity {

    // Declare the widget in about us activity
    private TabHost tabHost;
    private TextView textView17,textView22,textView24,textView28,textView29,textView26;
    private ImageView imageView,imageView2,imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.aboutToolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        //Create first tabhost

        tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Tab 1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Developer");
        tabHost.addTab(spec);

        //Create second tab
        spec = tabHost.newTabSpec("Tab 2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Support");
        tabHost.addTab(spec);


        textView17 = (TextView)findViewById(R.id.textView17);
        textView22= (TextView)findViewById(R.id.textView22);
        textView24= (TextView)findViewById(R.id.textView24);
        textView28= (TextView)findViewById(R.id.textView28);
        textView29= (TextView)findViewById(R.id.textView29);
        textView26= (TextView)findViewById(R.id.textView26);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        Glide.with(this).load("https://dab1nmslvvntp.cloudfront.net/wp-content/uploads/2016/02/1456222922Google-Maps-New-Icon.png").into(imageView);
        Glide.with(this).load("https://pbs.twimg.com/profile_images/875087697177567232/Qfy0kRIP_400x400.jpg").into(imageView2);
        Glide.with(this).load("https://pbs.twimg.com/profile_images/616309728688238592/pBeeJQDQ.png").into(imageView3);
//                "Whale information: http://www.environment.gov.au/marine/marine-species/cetaceans/whale-and-dolphin-watching \n\n" +
//                "and https://www.afsc.noaa.gov/nmml/species/index_ceta.php\n\n " +
//                "Google Map Api V2\n \n" +
//                "Twitter Api\n \n" +
//                "Awesome Android UI Design: https://github.com/wasabeef/awesome-android-ui");
        textView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/d/u/0/viewer?mid=1btvEVLs_vqVG_DRD9pFkx3AOrRI&ll=-38.48295571273258%2C145.204496383667&z=12"));
                startActivity(browser);
            }

        });

        textView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.environment.gov.au/marine/marine-species/cetaceans/whale-and-dolphin-watching"));
                startActivity(browser);
            }

        });
        textView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.afsc.noaa.gov/nmml/species/index_ceta.php"));
                startActivity(browser);
            }

        });


        textView28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/maps/documentation/android-api/"));
                startActivity(browser);
            }

        });

        textView29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://dev.twitter.com/twitterkit/android/overview"));
                startActivity(browser);
            }

        });

        textView26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/wasabeef/awesome-android-ui"));
                startActivity(browser);
            }

        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/maps/documentation/android-api/"));
                startActivity(browser);
            }

        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://dev.twitter.com/twitterkit/android/overview"));
                startActivity(browser);
            }

        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/wasabeef/awesome-android-ui"));
                startActivity(browser);
            }

        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
