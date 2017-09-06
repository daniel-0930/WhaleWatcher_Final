package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class BlowActivity extends AppCompatActivity {

    private ImageView blueblow;
    private ImageView spermblow;
    private ImageView humpbackblow;
    private ImageView rightblow;
    private ImageView killerblow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blow);
        Toolbar toolbar = (Toolbar) findViewById(R.id.blowToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.revertFabBlow);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reselect characteristic filter?", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent backIntent = new Intent(BlowActivity.this, SelectionActivity.class);
                                startActivity(backIntent);
                                finish();
                            }
                        }).show();
            }

        });

        Intent whichIntent = getIntent();
        String selectionPart =whichIntent.getStringExtra("which");

        blueblow = (ImageView)findViewById(R.id.blueblow);
        spermblow = (ImageView)findViewById(R.id.spermblow);
        humpbackblow = (ImageView)findViewById(R.id.humpbackblow);
        rightblow = (ImageView)findViewById(R.id.rightblow);
        killerblow = (ImageView) findViewById(R.id.killerblow);

        if(selectionPart.equals("first")){

            blueblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SurfaceActivity.class);
                    newIntent.putExtra("which","blow");
                    startActivity(newIntent);
                }
            });



            humpbackblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Humpback Whale");
                    startActivity(newIntent);
                    finish();
                }
            });

            spermblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Sperm Whale");
                    startActivity(newIntent);
                    finish();
                }
            });

            rightblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Southern Right Whale");
                    startActivity(newIntent);
                    finish();
                }
            });

            killerblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Killer Whale");
                    startActivity(newIntent);
                    finish();
                }
            });


        } else if(selectionPart.equals("diving")){
            spermblow.setVisibility(View.GONE);
            humpbackblow.setVisibility(View.GONE);
            rightblow.setVisibility(View.GONE);
            killerblow.setVisibility(View.GONE);
            blueblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SurfaceActivity.class);
                    newIntent.putExtra("which","blow and diving");
                    startActivity(newIntent);
                }
            });

        } else if(selectionPart.equals("surfacing")){
            rightblow.setVisibility(View.GONE);
            killerblow.setVisibility(View.GONE);
            spermblow.setVisibility(View.GONE);
            humpbackblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Humpback Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            blueblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Sei Whale");
                    startActivity(newIntent);
                    finish();
                }
            });

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
