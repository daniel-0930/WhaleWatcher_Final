package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;

public class SelectionActivity extends AppCompatActivity {

//    private ImageButton blowButton;
//    private ImageButton surfaceButton;
//    private ImageButton diveButton;

    String[] behaviorName = {
            "Surfacing",
            "Blow",
            "Diving"
    };


    Integer[] behaviorpic={
            R.drawable.surfacing,
            R.drawable.blow,
            R.drawable.diving
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Toolbar toolbar = (Toolbar) findViewById(R.id.selectionToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        behaviorMainAdapter adapter = new behaviorMainAdapter(this, behaviorName, behaviorpic);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = behaviorName[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                switch (Slecteditem){
                    case "Surfacing":
                        //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        Intent surfaceIntent = new Intent(SelectionActivity.this,SurfaceActivity.class);
                        surfaceIntent.putExtra("which","first");
                        startActivity(surfaceIntent);
                        break;
                    case "Blow":
                        Intent blowIntent = new Intent(SelectionActivity.this,BlowActivity.class);
                        blowIntent.putExtra("which","first");
                        startActivity(blowIntent);
                        break;
                    case "Diving":
                        Intent diveIntent = new Intent(SelectionActivity.this, DiveActivity.class);
                        diveIntent.putExtra("which","first");
                        startActivity(diveIntent);
                        break;

                }

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
