package com.a310p.radical.whalewatcher_final;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import java.io.File;
import android.graphics.Bitmap;
import java.io.InputStream;
import android.graphics.BitmapFactory;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TestActivity extends AppCompatActivity {

    private Button testButton1;
    private Button testButton2;
    private ImageView imageView;
    private Button testButton3;

    private Bitmap thisWillBeUpload;

    private FirebaseStorage storage;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 1;
    private static final int RESULT_LOAD_IMG = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        storage = FirebaseStorage.getInstance();

        testButton1 = (Button)findViewById(R.id.testCaptureButton);
        testButton2 = (Button)findViewById(R.id.testUploadButton);
        imageView = (ImageView)findViewById(R.id.testImage);
        testButton3 = (Button)findViewById(R.id.testUploadToFireBase);

        testButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            }
        });

        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
        testButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("Gallery");
                String uid = dbReference.push().getKey();
                dbReference.child(uid).setValue(uid);
                StorageReference storageRef = storage.getReference().child("whale").child(uid+".jpg");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thisWillBeUpload.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = storageRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TestActivity.this,"Failed",Toast.LENGTH_SHORT);
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(TestActivity.this,"Succeed",Toast.LENGTH_SHORT);
                    }
                });
            }
        });





    }

    private File getFile(){

        File image_file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"whale.jpg");

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE && resultCode == TestActivity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            thisWillBeUpload = photo;
            imageView.setImageBitmap(photo);
        } else if (requestCode == RESULT_LOAD_IMG && resultCode == TestActivity.RESULT_OK){
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
                thisWillBeUpload = selectedImage;
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(TestActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }

    }
}
