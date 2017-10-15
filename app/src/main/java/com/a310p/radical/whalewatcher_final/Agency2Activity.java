package com.a310p.radical.whalewatcher_final;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.a310p.radical.whalewatcher_final.Models.Agency;

import java.util.ArrayList;

public class Agency2Activity extends AppCompatActivity {

    private ArrayList<Agency> agencyArrayList; //Arraylist for agency
    private RecyclerView listRecycleView; // the recyclerview of agency
    private AgencyAdapter AgencyAdapter; //the adapter of agency
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency2); // generated to linked from xml file

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // set toolbar

        //Arraylist of agency
        agencyArrayList = new ArrayList<>();
        agencyArrayList.add(new Agency("http://www.dolphinswims.com.au/",
                "Sea All Dolphin Swims",
                "6, Queesncliff Boat Harbour, 3, Queenscliff VIC 3225",
                "http://dolphinswims.com.au/wp-content/uploads/2016/12/logo.png"));
        agencyArrayList.add(new Agency("http://www.wildlifecoastcruises.com.au/",
                "Wildlife Coast Cruise",
                "11/13 The Esplanade, Cowes VIC 3922",
                "http://www.wildlifecoastcruises.com.au/wp-content/themes/BayDigital-WILDLIFECOASTCRUISES/img/logo.png"));
        agencyArrayList.add(new Agency("https://visitwarrnambool.com.au/",
                "Warrnambool Visitor Information Centre",
                "89 Merri St, Warrnambool VIC 3280",
                "https://visitwarrnambool.com.au/assets-warrnambool/Uploads/beach4.jpg"));
        agencyArrayList.add(new Agency(
                "http://www.southerncoastcharters.com.au",
                "Southern Coast Charters",
                "Gipps St, Port Fairy VIC 3284",
                "http://www.southerncoastcharters.com.au/images/slider_images/southernexplorer_001c.jpg"));
        agencyArrayList.add(new Agency(
                "https://merimbulamarina.com",
                "Merimbula Marina",
                "Public Jetty, Market Street, Merimbula NSW 254",
                "https://merimbulamarina.com/wp-content/uploads/MERIM-MARINA-masterlogo-smaller.png"));
        agencyArrayList.add(new Agency(
                "http://www.moonrakercharters.com.au",
                "Moonraker Dolphin Swims",
                "Esplanade Rd, Sorrento VIC 3943" ,
                "http://www.moonrakercharters.com.au/wp-content/uploads/2017/07/dolphinswim.jpg"));


        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        // Build up recycler view
        listRecycleView = (RecyclerView)findViewById(R.id.listRecycleView_agency);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        listRecycleView.setLayoutManager(layoutManager);
        listRecycleView.addItemDecoration(new Agency2Activity.AgencyItemDecoration(1,35,true));
        listRecycleView.setItemAnimator(new DefaultItemAnimator());
        AgencyAdapter = new AgencyAdapter(this,agencyArrayList);
        listRecycleView.setAdapter(AgencyAdapter);




    }


    // Modify item and their distance
    public class AgencyItemDecoration extends RecyclerView.ItemDecoration{
        private int spancount;
        private int spacing;
        private boolean includeEdge;

        public AgencyItemDecoration(int spancount, int spacing, boolean includeEdge) {
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

    // Set up for toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


