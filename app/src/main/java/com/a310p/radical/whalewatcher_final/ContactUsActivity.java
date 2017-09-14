package com.a310p.radical.whalewatcher_final;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button startBtn = (Button) findViewById(R.id.sentbtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });


    }


    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"yinhongr@gmail.com"};
        String[] CC = {""};
        String messageString = "";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        EditText message = (EditText) findViewById(R.id.Message);
        messageString = message.getText().toString();


        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback to the team");
        emailIntent.putExtra(Intent.EXTRA_TEXT, messageString);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Sent Successful", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
