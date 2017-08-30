package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.a310p.radical.whalewatcher_final.Models.Whale;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private TextView orLabel;
    private LinearLayout selectionTable;
    private RadioButton largeRadio;
    private RadioButton smallRadio;
    private RadioButton allRadio;

    private CheckBox blueCheck;
    private CheckBox whiteCheck;
    private CheckBox greyCheck;
    private CheckBox blackCheck;

    private Spinner monthSpinner;

    private Button searchButton;
    private Button clearButton;

    private ListView searchList;

    private WhaleSearchAdapter whaleSearchAdapter;
    private ArrayList<Whale> whaleList;
    private DatabaseHelper databaseHelper;
    private Whale whale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        if(databaseHelper.getAllWhale().size() == 0){
            databaseHelper.createWhaleDatabase();
        }

        searchView = (SearchView)findViewById(R.id.searchView);
        orLabel = (TextView)findViewById(R.id.orLabel);
        selectionTable = (LinearLayout)findViewById(R.id.selectionTable);
        largeRadio = (RadioButton)findViewById(R.id.largeRadio);
        smallRadio = (RadioButton)findViewById(R.id.smallRadio);
        allRadio = (RadioButton)findViewById(R.id.allRadio);

        blueCheck = (CheckBox)findViewById(R.id.blueCheck);
        whiteCheck = (CheckBox)findViewById(R.id.whiteCheck);
        greyCheck = (CheckBox)findViewById(R.id.greyCheck);
        blackCheck = (CheckBox)findViewById(R.id.blackCheck);

        monthSpinner = (Spinner)findViewById(R.id.monthSpinner);

        searchButton = (Button)findViewById(R.id.searchButton);
        clearButton = (Button)findViewById(R.id.clearButton);

        searchList = (ListView)findViewById(R.id.searchList);

        whaleList = new ArrayList<>();
        whaleSearchAdapter = new WhaleSearchAdapter(this,whaleList);
        whale = new Whale();

        searchList.setAdapter(whaleSearchAdapter);
        searchView.setOnQueryTextListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setQuery("",false);
                searchView.setAlpha(1.0f);
                orLabel.setAlpha(1.0f);
                selectionTable.setAlpha(1.0f);
            }
        });




            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchView.setAlpha(0.0f);
                    whaleList = new ArrayList<>(databaseHelper.getAllWhale().values());
                    ArrayList<Whale> currentWhaleList = new ArrayList<>();
                    String month = monthSpinner.getSelectedItem().toString();
                    if(largeRadio.isChecked())
                    {
                        for(Whale w : whaleList)
                        {
                            if(blueCheck.isChecked()){
                                if(w.getLength()>=10 && w.getPossibleMonth().contains(month) && w.getFeature().contains("blue") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(blackCheck.isChecked()){
                                if(w.getLength()>=10 && w.getPossibleMonth().contains(month) && w.getFeature().contains("black") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(whiteCheck.isChecked()){
                                if(w.getLength()>=10 && w.getPossibleMonth().contains(month) && w.getFeature().contains("white") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(greyCheck.isChecked()){
                                if(w.getLength()>=10 && w.getPossibleMonth().contains(month) && ( w.getFeature().contains("gray")||w.getFeature().contains("grey")) &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }


                        }

                    }else if(smallRadio.isChecked()){
                        for(Whale w : whaleList)
                        {
                            if(blueCheck.isChecked()){
                                if(w.getLength()<=10 && w.getPossibleMonth().contains(month) && w.getFeature().contains("blue") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(blackCheck.isChecked()){
                                if(w.getLength()<=10 && w.getPossibleMonth().contains(month) && w.getFeature().contains("black") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(whiteCheck.isChecked()){
                                if(w.getLength()<=10 && w.getPossibleMonth().contains(month) && w.getFeature().contains("white") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(greyCheck.isChecked()){
                                if(w.getLength()<=10 && w.getPossibleMonth().contains(month) && ( w.getFeature().contains("gray")||w.getFeature().contains("grey")) &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }


                        }
                    }else if(allRadio.isChecked()){
                        for(Whale w : whaleList)
                        {
                            if(blueCheck.isChecked()){
                                if(w.getPossibleMonth().contains(month) && w.getFeature().contains("blue") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(blackCheck.isChecked()){
                                if(w.getPossibleMonth().contains(month) && w.getFeature().contains("black") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(whiteCheck.isChecked()){
                                if(w.getPossibleMonth().contains(month) && w.getFeature().contains("white") &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }

                            if(greyCheck.isChecked()){
                                if(w.getPossibleMonth().contains(month) &&( w.getFeature().contains("gray")||w.getFeature().contains("grey")) &&checkExist(currentWhaleList,w))
                                {
                                    currentWhaleList.add(w);
                                }
                            }


                        }


                    }

                    if(currentWhaleList.size() == 0)
                    {
                        Toast.makeText(SearchActivity.this, "Whale you have searched is not existed", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        // Set the table to new result list
                        whaleSearchAdapter = new WhaleSearchAdapter(SearchActivity.this,currentWhaleList);
                        searchList.setAdapter(whaleSearchAdapter);
                        whaleSearchAdapter.notifyDataSetChanged();
                    }
                }
            });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setAlpha(1.0f);
                largeRadio.setChecked(false);
                smallRadio.setChecked(false);
                allRadio.setChecked(false);

                blueCheck.setChecked(false);
                whiteCheck.setChecked(false);
                blackCheck.setChecked(false);
                greyCheck.setChecked(false);

                monthSpinner.setSelection(0);
            }
        });

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Whale whale = whaleList.get(position);
                Intent informationIntent = new Intent(SearchActivity.this,WhaleInformationActivity.class);
                informationIntent.putExtra("whale",whale);
                startActivity(informationIntent);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        whaleList = new ArrayList<>(databaseHelper.getAllWhale().values());
        ArrayList<Whale> currentWhaleList = new ArrayList<>();

        //Put the result into searching result array
        for(Whale w : whaleList)
        {
            if(w.getName().toLowerCase().contains(query.toLowerCase()))
            {
                currentWhaleList.add(w);
            }
        }
        // When monster not found, the message will pop out
        if(currentWhaleList.size() == 0)
        {
            Toast.makeText(SearchActivity.this, "Whale you have searched is not existed", Toast.LENGTH_SHORT).show();

        }
        else{
            // Set the table to new result list
            whaleSearchAdapter = new WhaleSearchAdapter(this,currentWhaleList);
            searchList.setAdapter(whaleSearchAdapter);
            whaleSearchAdapter.notifyDataSetChanged();
        }


        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!newText.trim().equals("")){
            orLabel.setAlpha(0.0f);
            selectionTable.setAlpha(0.0f);
        }
        if(newText.trim().equals("")){
            orLabel.setAlpha(1.0f);
            selectionTable.setAlpha(1.0f);
        }
        return true;
    }


    public boolean checkExist(ArrayList<Whale> list, Whale whale){
        for(Whale w : list){
            if(w.getName().equals(whale.getName()))
            {
                return false;
            }
        }
        return true;
    }

}
