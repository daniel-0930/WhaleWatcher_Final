package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a310p.radical.whalewatcher_final.Models.Whale;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

    private ListView searchList;

    private ArrayList<Whale> whaleArrayList;
    private WhaleSearchAdapter whaleSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        whaleArrayList = getIntent().getParcelableArrayListExtra("currentwhaleList");
        searchList = (ListView)findViewById(R.id.searchList);

        whaleSearchAdapter = new WhaleSearchAdapter(this,whaleArrayList);
        searchList.setAdapter(whaleSearchAdapter);

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Whale whale = whaleArrayList.get(position);
                Intent informationIntent = new Intent(SearchResultActivity.this,WhaleInformationActivity.class);
                informationIntent.putExtra("whale",whale);
                startActivity(informationIntent);
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
