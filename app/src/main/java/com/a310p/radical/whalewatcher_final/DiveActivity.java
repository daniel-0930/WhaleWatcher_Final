package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class DiveActivity extends AppCompatActivity {

    private ImageView diveblue;
    private ImageView divesperm;
    private ImageView divefin;
    private ImageView divehumpback;
    private ImageView divekiller;
    private ImageView diveright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        diveblue = (ImageView)findViewById(R.id.diveblue);
        divefin = (ImageView)findViewById(R.id.divefin);
        divehumpback = (ImageView)findViewById(R.id.divehumpack);
        divekiller = (ImageView)findViewById(R.id.divekiller);
        diveright = (ImageView)findViewById(R.id.diveright);
        divesperm = (ImageView)findViewById(R.id.divsperm);

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
                Intent newIntent = new Intent(DiveActivity.this,SiteSelectionActivity.class);
                newIntent.putExtra("whalename","Blue Whale");
                startActivity(newIntent);
                finish();
            }
        });

        divehumpback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,SiteSelectionActivity.class);
                newIntent.putExtra("whalename","Humpback Whale");
                startActivity(newIntent);
                finish();
            }
        });
        divekiller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,SiteSelectionActivity.class);
                newIntent.putExtra("whalename","Killer Whale");
                startActivity(newIntent);
                finish();
            }
        });
        diveright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,SiteSelectionActivity.class);
                newIntent.putExtra("whalename","Southern Right Whale");
                startActivity(newIntent);
                finish();
            }
        });
        divesperm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DiveActivity.this,SiteSelectionActivity.class);
                newIntent.putExtra("whalename","Sperm Whale");
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
    }

}
