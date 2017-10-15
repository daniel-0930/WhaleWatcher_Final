package com.a310p.radical.whalewatcher_final;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.roger.catloadinglibrary.CatLoadingView;

public class AgencyDetail2Activity extends AppCompatActivity {


    private WebView agency_web_view ;
    private CatLoadingView catView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //web view of the web page in agency
        agency_web_view = (WebView)findViewById(R.id.webView);
        catView = new CatLoadingView();
        Intent i = getIntent();
        String websiteurl = i.getStringExtra("agency");

        agency_web_view.getSettings().setJavaScriptEnabled(true); // enable javascript

        final AppCompatActivity activity = this;

        agency_web_view.setWebViewClient(new AgencyWebViewClient(catView,AgencyDetail2Activity.this));

        agency_web_view.loadUrl(websiteurl);




    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //Show a progress dialog, and describe how webview show websites

    private class AgencyWebViewClient extends WebViewClient {
        private CatLoadingView catLoadingView;
        private Context context;

        public AgencyWebViewClient(CatLoadingView catLoadingView, Context context) {
            this.catLoadingView = catLoadingView;
            this.context = context;
            catLoadingView.show(getSupportFragmentManager(),"");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);
            catLoadingView.dismiss();
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(context, description, Toast.LENGTH_SHORT).show();
        }
    }

}
