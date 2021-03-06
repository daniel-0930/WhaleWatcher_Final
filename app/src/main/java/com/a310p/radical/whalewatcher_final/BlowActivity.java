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

public class BlowActivity extends AppCompatActivity {

    private ImageView blueblow;
    private ImageView spermblow;
    private ImageView humpbackblow;
    private ImageView rightblow;
    private ImageView killerblow;

    private ImageButton blueblowButton,spermblowButton,humpbackblowButton,rightblowButton,killerblowButton;

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

        blueblow = (ImageView)findViewById(R.id.blueBlow);
        spermblow = (ImageView)findViewById(R.id.spermBlow);
        humpbackblow = (ImageView)findViewById(R.id.humpbackBlow);
        rightblow = (ImageView)findViewById(R.id.rightBlow);
        killerblow = (ImageView) findViewById(R.id.killerBlow);


        blueblowButton = (ImageButton) findViewById(R.id.blueBlowButton);
        spermblowButton = (ImageButton)findViewById(R.id.spermBlowButton);
        humpbackblowButton = (ImageButton)findViewById(R.id.humpbackBlowButton);
        rightblowButton = (ImageButton)findViewById(R.id.rightBlowButton);
        killerblowButton = (ImageButton) findViewById(R.id.killerBlowButton);

        if(selectionPart.equals("first")){

            blueblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SurfaceActivity.class);
                    newIntent.putExtra("which","blow");
                    startActivity(newIntent);
                }
            });

            blueblowButton.setOnClickListener(new View.OnClickListener() {
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
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Humpback Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            humpbackblowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Humpback Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });

            spermblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sperm Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            spermblowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sperm Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });

            rightblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Southern Right Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            rightblowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Southern Right Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });

            killerblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Killer Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            killerblowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Killer Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });


        } else if(selectionPart.equals("diving")){
            spermblow.setVisibility(View.GONE);
            humpbackblow.setVisibility(View.GONE);
            rightblow.setVisibility(View.GONE);
            killerblow.setVisibility(View.GONE);
            spermblowButton.setVisibility(View.GONE);
            humpbackblowButton.setVisibility(View.GONE);
            rightblowButton.setVisibility(View.GONE);
            killerblowButton.setVisibility(View.GONE);
            blueblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,SurfaceActivity.class);
                    newIntent.putExtra("which","blow and diving");
                    startActivity(newIntent);
                }
            });
            blueblowButton.setOnClickListener(new View.OnClickListener() {
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
            rightblowButton.setVisibility(View.GONE);
            killerblowButton.setVisibility(View.GONE);
            spermblowButton.setVisibility(View.GONE);
            humpbackblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Humpback Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            humpbackblowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Humpback Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            blueblow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sei Whale");
                    newIntent.putExtra("whale",whale);
                    startActivity(newIntent);
                    finish();
                }
            });
            blueblowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newIntent = new Intent(BlowActivity.this,WhaleInformationActivity.class);
                    Whale whale = findCertainWhale("Sei Whale");
                    newIntent.putExtra("whale",whale);
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
