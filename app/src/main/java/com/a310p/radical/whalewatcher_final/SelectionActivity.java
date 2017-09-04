package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SelectionActivity extends AppCompatActivity {

    private RadioButton blowButton;
    private RadioButton surfaceButton;
    private RadioButton diveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        blowButton =(RadioButton)findViewById(R.id.blowRadio);
        surfaceButton = (RadioButton)findViewById(R.id.surfaceRadio);
        diveButton = (RadioButton)findViewById(R.id.diveRadio);

        blowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent blowIntent = new Intent(SelectionActivity.this,BlowActivity.class);
                blowIntent.putExtra("which","first");
                startActivity(blowIntent);
            }
        });

        surfaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent surfaceIntent = new Intent(SelectionActivity.this,SurfaceActivity.class);
                surfaceIntent.putExtra("which","first");
                startActivity(surfaceIntent);
            }
        });

        diveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent diveIntent = new Intent(SelectionActivity.this, DiveActivity.class);
                diveIntent.putExtra("which","first");
                startActivity(diveIntent);
            }
        });



    }

    @Override
    public void onBackPressed(){
        Intent newIntent = new Intent(SelectionActivity.this, SiteActivity.class);
        startActivity(newIntent);
        finish();
    }
}
