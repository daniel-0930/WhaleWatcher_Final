package com.a310p.radical.whalewatcher_final;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.a310p.radical.whalewatcher_final.Models.Whale;

import java.util.ArrayList;

public class ListWhaleActivity extends AppCompatActivity {

    private RecyclerView listRecycleView;
    private WhaleListAdapter whaleListAdapter;
    private ArrayList<Whale> whaleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_whale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        listRecycleView = (RecyclerView)findViewById(R.id.listRecycleView);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        if(databaseHelper.getAllWhale().size() == 0){
            databaseHelper.createWhaleDatabase();
        }

        whaleList = new ArrayList<>(databaseHelper.getAllWhale().values());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        listRecycleView.setLayoutManager(layoutManager);
        listRecycleView.addItemDecoration(new WhaleItemDecoration(1,35,true));
        listRecycleView.setItemAnimator(new DefaultItemAnimator());
        whaleListAdapter = new WhaleListAdapter(this,whaleList);
        listRecycleView.setAdapter(whaleListAdapter);




    }

    public class WhaleItemDecoration extends RecyclerView.ItemDecoration{
        private int spancount;
        private int spacing;
        private boolean includeEdge;

        public WhaleItemDecoration(int spancount, int spacing, boolean includeEdge) {
            this.spancount = spancount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spancount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spancount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spancount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spancount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spancount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spancount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spancount) {
                    outRect.top = spacing; // item top
                }
            }
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
