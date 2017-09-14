package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.a310p.radical.whalewatcher_final.Models.Whale;

import java.util.ArrayList;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

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
    private RadioGroup lengthGroup;

    private WhaleSearchAdapter whaleSearchAdapter;
    private ArrayList<Whale> whaleList;
    private DatabaseHelper databaseHelper;
    private Whale whale;
    private ArrayList<Whale> currentWhaleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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

        lengthGroup = (RadioGroup)findViewById(R.id.lengthGroup);

        whaleList = new ArrayList<>();
        whale = new Whale();
        searchView.setOnQueryTextListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setQuery("",false);
                searchView.setVisibility(View.VISIBLE);
                orLabel.setVisibility(View.VISIBLE);
                selectionTable.setVisibility(View.VISIBLE);
                currentWhaleList = new ArrayList<Whale>();
            }
        });




            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentWhaleList = new ArrayList<>();
                    orLabel.setVisibility(View.GONE);
                    searchView.setVisibility(View.GONE);
                    whaleList = new ArrayList<>(databaseHelper.getAllWhale().values());
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
                            if(!greyCheck.isChecked()&&!whiteCheck.isChecked()&&!blackCheck.isChecked()&&!blueCheck.isChecked()){
                                if(w.getPossibleMonth().contains(month)&&checkExist(currentWhaleList,w)){
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
                            if(!greyCheck.isChecked()&&!whiteCheck.isChecked()&&!blackCheck.isChecked()&&!blueCheck.isChecked()){
                                if(w.getPossibleMonth().contains(month)&&checkExist(currentWhaleList,w)){
                                    currentWhaleList.add(w);
                                }
                            }


                        }
                    }else if((allRadio.isChecked())|| (!largeRadio.isChecked()&&!smallRadio.isChecked()&&!allRadio.isChecked())){
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
                            if(!greyCheck.isChecked()&&!whiteCheck.isChecked()&&!blackCheck.isChecked()&&!blueCheck.isChecked()){
                                if(w.getPossibleMonth().contains(month)&&checkExist(currentWhaleList,w)){
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
                        Intent newIntent = new Intent(SearchActivity.this,SearchResultActivity.class);
                        newIntent.putParcelableArrayListExtra("currentwhaleList",currentWhaleList);
                        startActivity(newIntent);
                    }
                }
            });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setVisibility(View.VISIBLE);
                lengthGroup.clearCheck();
//                largeRadio.setChecked(false);
//                smallRadio.setChecked(false);
//                allRadio.setChecked(false);

                blueCheck.setChecked(false);
                whiteCheck.setChecked(false);
                blackCheck.setChecked(false);
                greyCheck.setChecked(false);

                monthSpinner.setSelection(0);
            }
        });


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        whaleList = new ArrayList<>(databaseHelper.getAllWhale().values());
        currentWhaleList = new ArrayList<>();
        //Put the result into searching result array
        for(Whale w : whaleList)
        {
            if(w.getName().toLowerCase().contains(query.toLowerCase()))
            {
                currentWhaleList.add(w);
            }
        }

        if(currentWhaleList.size() == 0)
        {
            Toast.makeText(SearchActivity.this, "Whale you have searched is not existed", Toast.LENGTH_SHORT).show();

        }
        else{
            Intent newIntent = new Intent(SearchActivity.this,SearchResultActivity.class);
            newIntent.putParcelableArrayListExtra("currentwhaleList",currentWhaleList);
            startActivity(newIntent);
        }


        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!newText.trim().equals("")){
            orLabel.setVisibility(View.GONE);
            selectionTable.setVisibility(View.GONE);
        }
        if(newText.trim().equals("")){
            orLabel.setVisibility(View.VISIBLE);
            selectionTable.setVisibility(View.VISIBLE);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
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

