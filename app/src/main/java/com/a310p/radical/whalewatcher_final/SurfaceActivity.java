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

public class SurfaceActivity extends AppCompatActivity {

    private ImageView surfblue;
    private ImageView surfsperm;
    private ImageView surfright;
    private ImageView surfsei;
    private ImageView surffin;
    private ImageView surfkiller;
    private ImageView surfminke;
    private ImageButton surfblueButton,surfspermButton,surfrightButton,surfseiButton,surffinButton,surfkillerButton,surfminkeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        surfblue = (ImageView) findViewById(R.id.surfBlue);
        surfsperm = (ImageView) findViewById(R.id.surfSperm);
        surfright = (ImageView) findViewById(R.id.surfRight);
        surfsei = (ImageView) findViewById(R.id.surfSei);
        surffin = (ImageView) findViewById(R.id.surfFin);
        surfkiller = (ImageView) findViewById(R.id.surfKiller);
        surfminke = (ImageView) findViewById(R.id.surfMinke);

        surfblueButton = (ImageButton) findViewById(R.id.surfBlueButton);
        surfspermButton = (ImageButton) findViewById(R.id.surfSpermButton);
        surfrightButton = (ImageButton) findViewById(R.id.surfRightButton);
        surfseiButton = (ImageButton) findViewById(R.id.surfSeiButton);
        surffinButton = (ImageButton) findViewById(R.id.surfFinButton);
        surfkillerButton = (ImageButton) findViewById(R.id.surfKillerButton);
        surfminkeButton = (ImageButton) findViewById(R.id.surfMinkeButton);

        Intent whichIntent = getIntent();
        String selectionPart = whichIntent.getStringExtra("which");

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

        if (selectionPart.equals("first")) {
            surfblue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Blue Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfsperm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sperm Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Southern Right Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfsei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, BlowActivity.class);
                    newIntent.putExtra("which", "surfacing");
                    startActivity(newIntent);
                }
            });
            surffin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Fin Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfkiller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Killer Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Minke Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });

            surfblueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Blue Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfspermButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sperm Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfrightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Southern Right Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfseiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, BlowActivity.class);
                    newIntent.putExtra("which", "surfacing");
                    startActivity(newIntent);
                }
            });
            surffinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Fin Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfkillerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Killer Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminkeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Minke Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });

        } else if (selectionPart.equals("blow")) {
            surfkiller.setVisibility(View.GONE);
            surfright.setVisibility(View.GONE);
            surfsperm.setVisibility(View.GONE);
            surfkillerButton.setVisibility(View.GONE);
            surfrightButton.setVisibility(View.GONE);
            surfspermButton.setVisibility(View.GONE);
            surfblue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Blue Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfsei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sei Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surffin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Fin Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Minke Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });

            surfblueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Blue Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfseiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sei Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surffinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Fin Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminkeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Minke Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });


        } else if (selectionPart.equals("blow and diving")) {
            surfkiller.setVisibility(View.GONE);
            surfright.setVisibility(View.GONE);
            surfsperm.setVisibility(View.GONE);
            surfblue.setVisibility(View.GONE);

            surfkillerButton.setVisibility(View.GONE);
            surfrightButton.setVisibility(View.GONE);
            surfspermButton.setVisibility(View.GONE);
            surfblueButton.setVisibility(View.GONE);
            surfsei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sei Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surffin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Fin Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Minke Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });

            surfseiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sei Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surffinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Fin Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            surfminkeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(SurfaceActivity.this, WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Minke Whale");
                    newIntent.putExtra("whale", whale);
                    startActivity(newIntent);
                    finish();
                }
            });
        }
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
