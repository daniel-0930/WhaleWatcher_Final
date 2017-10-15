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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.a310p.radical.whalewatcher_final.Models.Whale;

import java.util.ArrayList;

public class DiveActivity extends AppCompatActivity {

    private ImageView diveblue;
    private ImageView divesperm;
    private ImageView divefin;
    private ImageView divehumpback;
    private ImageView divekiller;
    private ImageView diveright;

    private ImageButton diveblueButton,divespermButton,divefinButton,divehumpbackButton,divekillerButton,diverightButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        diveblue = (ImageView)findViewById(R.id.diveBlue);
        divefin = (ImageView)findViewById(R.id.diveFin);
        divehumpback = (ImageView)findViewById(R.id.diveHumpack);
        divekiller = (ImageView)findViewById(R.id.diveKiller);
        diveright = (ImageView)findViewById(R.id.diveRight);
        divesperm = (ImageView)findViewById(R.id.diveSperm);

        diveblueButton = (ImageButton) findViewById(R.id.diveBlueButton);
        divefinButton = (ImageButton)findViewById(R.id.diveFinButton);
        divehumpbackButton = (ImageButton)findViewById(R.id.diveHumpackButton);
        divekillerButton = (ImageButton)findViewById(R.id.diveKillerButton);
        diverightButton = (ImageButton)findViewById(R.id.diveRightButton);
        divespermButton = (ImageButton)findViewById(R.id.diveSpermButton);

        Intent whichIntent = getIntent();
        String selectionPart =whichIntent.getStringExtra("which");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.revertFabDive);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reselect characteristic filter?", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent backIntent = new Intent (DiveActivity.this,SelectionActivity.class);
                                startActivity(backIntent);
                                finish();
                            }
                        }).show();
            }
        });



        diveblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Blue Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });

        divehumpback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Humpback Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });
        divekiller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Killer Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });
        diveright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Southern Right Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });
        divesperm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Sperm Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });
        divefin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,BlowActivity.class);
                newIntent.putExtra("which","diving");
                startActivity(newIntent);
            }
        });




        diveblueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Blue Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });

        divehumpbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Humpback Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });
        divekillerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Killer Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });
        diverightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Southern Right Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });
        divespermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,WhaleInformationActivity.class);
                Whale whale = findCertainWhale("Sperm Whale");
                newIntent.putExtra("whale",whale);
                startActivity(newIntent);
                finish();
            }
        });
        divefinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,BlowActivity.class);
                newIntent.putExtra("which","diving");
                startActivity(newIntent);
            }
        });
    }

    public Whale findCertainWhale(String whalename){
        Whale whale = new Whale();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<Whale> whaleArrayList = new ArrayList<>(databaseHelper.getAllWhale().values());
        for(int i = 0; i<whaleArrayList.size();i++){
            if(whaleArrayList.get(i).getName().equals(whalename)){
                return whaleArrayList.get(i);
            }
        }

        return whale;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
