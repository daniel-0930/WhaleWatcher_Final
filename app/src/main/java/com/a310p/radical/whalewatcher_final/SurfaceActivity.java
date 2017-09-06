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

public class SurfaceActivity extends AppCompatActivity {

    private ImageView surfblue;
    private ImageView surfsperm;
    private ImageView surfright;
    private ImageView surfsei;
    private ImageView surffin;
    private ImageView surfkiller;
    private ImageView surfminke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        surfblue = (ImageView)findViewById(R.id.surfblue);
        surfsperm = (ImageView)findViewById(R.id.surfsperm);
        surfright = (ImageView)findViewById(R.id.surfright);
        surfsei = (ImageView)findViewById(R.id.surfsei);
        surffin = (ImageView)findViewById(R.id.surffin);
        surfkiller = (ImageView)findViewById(R.id.surfkiller);
        surfminke = (ImageView)findViewById(R.id.surfminke);

        Intent whichIntent = getIntent();
        String selectionPart =whichIntent.getStringExtra("which");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.revertFabSurf);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reselect characteristic filter?", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent backIntent = new Intent(SurfaceActivity.this, SelectionActivity.class);
                                startActivity(backIntent);
                                finish();
                            }
                        }).show();
            }

        });

        if(selectionPart.equals("first")){
            surfblue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Blue Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surfsperm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Sperm Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surfright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Southern Right Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surfsei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,BlowActivity.class);
                    newIntent.putExtra("which","surfacing");
                    startActivity(newIntent);
                }
            });
            surffin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Fin Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surfkiller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Killer Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Minke Whale");
                    startActivity(newIntent);
                    finish();
                }
            });

        } else if(selectionPart.equals("blow")){
            surfkiller.setVisibility(View.GONE);
            surfright.setVisibility(View.GONE);
            surfsperm.setVisibility(View.GONE);
            surfblue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Blue Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surfsei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Sei Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surffin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Fin Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Minke Whale");
                    startActivity(newIntent);
                    finish();
                }
            });


        } else if(selectionPart.equals("blow and diving")) {
            surfkiller.setVisibility(View.GONE);
            surfright.setVisibility(View.GONE);
            surfsperm.setVisibility(View.GONE);
            surfblue.setVisibility(View.GONE);
            surfsei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Sei Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surffin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Fin Whale");
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this,SiteSelectionActivity.class);
                    newIntent.putExtra("whalename","Minke Whale");
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
