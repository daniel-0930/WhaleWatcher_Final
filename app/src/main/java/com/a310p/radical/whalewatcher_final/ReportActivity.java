package com.a310p.radical.whalewatcher_final;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.a310p.radical.whalewatcher_final.Models.WhaleLocation;
import com.dd.CircularProgressButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportActivity extends AppCompatActivity {

    private EditText edtLocation,edtComment;
    private Spinner spnType;
    private ImageView imgWhale;
    private Button btnChooseLocation, btnTakePhoto, btnChoosePhoto;
    private CircularProgressButton btnsubmit;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 1;
    private static final int RESULT_LOAD_IMG = 2;

    private static final int EMAIL_SEND_CODE = 3;

    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        edtLocation = (EditText)findViewById(R.id.locationbox);
        spnType = (Spinner)findViewById(R.id.sprWhaleType);
        imgWhale = (ImageView)findViewById(R.id.imgwhaletaking);
        edtComment = (EditText)findViewById(R.id.commentsBox);
        btnChooseLocation = (Button)findViewById(R.id.btnLocation);
        btnTakePhoto = (Button)findViewById(R.id.btnTakePhoto);
        btnChoosePhoto = (Button)findViewById(R.id.btnChoosePhoto);

        imgWhale.setVisibility(View.GONE);
        edtLocation.setFocusable(false);
        edtLocation.setClickable(false);

        btnChooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReportActivity.this,SiteSelectionActivity.class);

                if(!spnType.getSelectedItem().equals("----------Whale Type----------")){
                    i.putExtra("type",String.valueOf(spnType.getSelectedItemPosition()));
                }

                if(!edtComment.getText().toString().equals("")){
                    i.putExtra("comment",edtComment.getText().toString());
                }
                if(uri != null){
                    String uriString = uri.toString();
                    i.putExtra("uri",uriString);
                }
                startActivity(i);
            }
        });

        Intent newI = getIntent();
        String location = newI.getStringExtra("location");
        String uriback = newI.getStringExtra("uriback");
        String typeback = newI.getStringExtra("typeback");
        String commentsback = newI.getStringExtra("commentback");


        if(location != null){
            edtLocation.setText(location);
        }

        if(uriback != null){
            uri = Uri.parse(uriback);
            imgWhale.setVisibility(View.VISIBLE);
            imgWhale.setImageURI(null);
            imgWhale.setImageURI(uri);
        }
        if(typeback != null){
            int i = Integer.valueOf(typeback);
            spnType.setSelection(i);
        }
        if(commentsback != null){
            edtComment.setText(commentsback);
        }


        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            }
        });

        btnChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });








        btnsubmit = (CircularProgressButton)findViewById(R.id.submitButton);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = edtLocation.getText().toString();
                String type = spnType.getSelectedItem().toString();
                String comments = edtComment.getText().toString();
                double latitude = 0;
                double longitude = 0;

                if(!location.equals("") && !type.equals("----------Whale Type----------")){
                    Calendar c = Calendar.getInstance();
                    String time = String.valueOf(c.get(Calendar.YEAR))+"-"+String.valueOf(c.get(Calendar.MONTH)+1)
                            +"-"+String.valueOf(c.get(Calendar.DATE))+"-"+String.valueOf(c.get(Calendar.HOUR_OF_DAY))
                            +"-"+String.valueOf(c.get(Calendar.MINUTE))+"-"+String.valueOf(c.get(Calendar.SECOND));

                    String[] locationNumbers = location.split(",");
                    latitude = Double.valueOf(locationNumbers[0]);
                    longitude = Double.valueOf(locationNumbers[1]);

                    WhaleLocation whaleLocation = new WhaleLocation(time,latitude,longitude);
                    if(type.equals("Blue Whale")){
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Blue Whale").child(String.valueOf(MainActivity.BLUENUMBER+1));
                        dbReference.setValue(whaleLocation);
                    } else if(type.equals("Fin Whale")){
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Fin Whale").child(String.valueOf(MainActivity.FINNUMBER+1));
                        dbReference.setValue(whaleLocation);
                    } else if(type.equals("Humpback Whale")){
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Humpback Whale").child(String.valueOf(MainActivity.HUMPBAKCNUMBER+1));
                        dbReference.setValue(whaleLocation);
                    }else if(type.equals("Killer Whale")){
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Killer Whale").child(String.valueOf(MainActivity.KILLERNUMBER+1));
                        dbReference.setValue(whaleLocation);
                    }else if(type.equals("Minke Whale")){
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Minke Whale").child(String.valueOf(MainActivity.MINKENUMBER+1));
                        dbReference.setValue(whaleLocation);
                    }else if(type.equals("Sei Whale")){
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Sei Whale").child(String.valueOf(MainActivity.SEINUMBER+1));
                        dbReference.setValue(whaleLocation);
                    }else if(type.equals("Southern Right Whale")){
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Southern Right Whale").child(String.valueOf(MainActivity.RIGHTNUMBER+1));
                        dbReference.setValue(whaleLocation);
                    }else if(type.equals("Sperm Whale")){
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Sperm Whale").child(String.valueOf(MainActivity.SPERMNUMBER+1));
                        dbReference.setValue(whaleLocation);
                    }



                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"460737263@qq.com"});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "#I saw a Whale");
                    String messageText = "I saw a whale ( "+type +" )at this place( " + location+" ) on " +String.valueOf(c.get(Calendar.YEAR))+"/"+String.valueOf(c.get(Calendar.MONTH)+1)
                            +"/"+String.valueOf(c.get(Calendar.DATE))+" "+String.valueOf(c.get(Calendar.HOUR_OF_DAY))
                            +":"+String.valueOf(c.get(Calendar.MINUTE))+":"+String.valueOf(c.get(Calendar.SECOND))+"\n"+comments+"\n\n"+"Regards,\n";
                    emailIntent.putExtra(Intent.EXTRA_TEXT, messageText);
                    if(uri != null){
                        emailIntent.setType("application/image");
                        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    } else{
                        emailIntent.setType("text/plain");
                    }
                    try{
                        startActivityForResult(Intent.createChooser(emailIntent, "Send your email in:"),EMAIL_SEND_CODE);
                        btnsubmit.setIndeterminateProgressMode(true);
                    }catch(ActivityNotFoundException ex){
                        Toast.makeText(ReportActivity.this,"NO Email Client",Toast.LENGTH_SHORT).show();
                    }
                    if(uri != null) {
                        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                        storageReference.child("whales2").putFile(uri);
                    }



                } else{
                    btnsubmit.setIndeterminateProgressMode(true);
                    if(location.equals("")){
                        edtLocation.setError("Please choose the location you saw a whale");
                        btnsubmit.setProgress(-1);
                        btnsubmit.setProgress(0);
                    }
                    if(type.equals("----------Whale Type----------")){
                        showADialog("Wrong!","Please Select a certain Type of Whale!");
                        btnsubmit.setProgress(-1);
                        btnsubmit.setProgress(0);
                    }
                }




            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE && resultCode == ReportActivity.RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgWhale.setVisibility(View.VISIBLE);
            imgWhale.setImageBitmap(photo);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(ReportActivity.this.getContentResolver(), photo, "Title", null);
            uri=Uri.parse(path);


        } else if (requestCode == RESULT_LOAD_IMG && resultCode == ReportActivity.RESULT_OK){
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgWhale.setVisibility(View.VISIBLE);
                imgWhale.setImageBitmap(selectedImage);
                uri = imageUri;
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ReportActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == EMAIL_SEND_CODE && requestCode == ReportActivity.RESULT_OK){
            btnsubmit.setProgress(100);
            Toast.makeText(ReportActivity.this,"Send Report Successfully",Toast.LENGTH_LONG).show();
        }

    }


    public void showADialog(String title,String information){
        AlertDialog.Builder dialogInfo = new AlertDialog.Builder(ReportActivity.this);
        dialogInfo.setTitle(title);
        dialogInfo.setMessage(information);
        final AlertDialog dialogNew = dialogInfo.create();
        dialogNew.show();
        final Handler handler = new Handler();
        final Runnable runable = new Runnable() {
            @Override
            public void run() {
                if(dialogNew.isShowing()){
                    dialogNew.dismiss();
                }
            }
        };
        dialogInfo.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runable);
            }
        });

        handler.postDelayed(runable,3000);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
