package com.a310p.radical.whalewatcher_final;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a310p.radical.whalewatcher_final.Models.WhaleLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SiteSelectionActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private CameraPosition cameraPosition;

    private GeoDataClient geoDataClient;
    private PlaceDetectionClient placeDetectionClient;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private final LatLng defaultLocation = new LatLng(-37.8840,145.0266);
    private static final int DEFUALT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private boolean locationPermissionGranted;

    private Location lastKnownLocation;

    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    private String whalename;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        Intent whaleIntent = getIntent();
        whalename = whaleIntent.getStringExtra("whalename");

        setContentView(R.layout.activity_site_selection);
        geoDataClient = Places.getGeoDataClient(this,null);
        placeDetectionClient = Places.getPlaceDetectionClient(this,null);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapSelectionFragment);
        mapFragment.getMapAsync(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState){
        if(map != null){
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION,lastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        getLocationPermission();

        updateLocationUI();

        getDeviceLocation();
        showADialog("Congratulations!","Lucky! You saw a "+whalename);


        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {



                if(!checkLocationInTheSea(latLng)){
                    showADialog("Wrong Place!","Whales could not live without SEA!");
                } else{
                    //Dialog --> sure? if sure add to firebase
                    //if not dialog dismiss
                    Calendar c = Calendar.getInstance();
                    String timePhase = String.valueOf(c.get(Calendar.YEAR))+"/"+String.valueOf(c.get(Calendar.MONTH))
                            +"/"+String.valueOf(c.get(Calendar.DATE))+" "+String.valueOf(c.get(Calendar.HOUR_OF_DAY))
                            +":"+String.valueOf(c.get(Calendar.MINUTE))+":"+String.valueOf(c.get(Calendar.SECOND));
                    MarkerOptions options = new MarkerOptions().position(latLng)
                            .title(whalename).snippet(timePhase).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    Marker thisMarker = map.addMarker(options);


                    final double latitude= latLng.latitude;
                    final double longitude = latLng.longitude;
                    AlertDialog.Builder builder=new AlertDialog.Builder(SiteSelectionActivity.this);
                    builder.setTitle("Are you sure?");
                    builder.setMessage("Are you sure you saw a whale here?");
                    builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Calendar c = Calendar.getInstance();
                            String time = String.valueOf(c.get(Calendar.YEAR))+"-"+String.valueOf(c.get(Calendar.MONTH))
                                    +"-"+String.valueOf(c.get(Calendar.DATE))+"-"+String.valueOf(c.get(Calendar.HOUR_OF_DAY))
                                    +"-"+String.valueOf(c.get(Calendar.MINUTE))+"-"+String.valueOf(c.get(Calendar.SECOND));
                            WhaleLocation whaleLocation = new WhaleLocation(time,latitude,longitude);
                            if(whalename.equals("Blue Whale")){
                                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Blue Whale").child(String.valueOf(SiteActivity.BLUENUMBER+1));
                                dbReference.setValue(whaleLocation);
                            } else if(whalename.equals("Fin Whale")){
                                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Fin Whale").child(String.valueOf(SiteActivity.FINNUMBER+1));
                                dbReference.setValue(whaleLocation);
                            } else if(whalename.equals("Humpback Whale")){
                                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Humpback Whale").child(String.valueOf(SiteActivity.HUMPBAKCNUMBER+1));
                                dbReference.setValue(whaleLocation);
                            }else if(whalename.equals("Killer Whale")){
                                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Killer Whale").child(String.valueOf(SiteActivity.KILLERNUMBER+1));
                                dbReference.setValue(whaleLocation);
                            }else if(whalename.equals("Minke Whale")){
                                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Minke Whale").child(String.valueOf(SiteActivity.MINKENUMBER+1));
                                dbReference.setValue(whaleLocation);
                            }else if(whalename.equals("Sei Whale")){
                                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Sei Whale").child(String.valueOf(SiteActivity.SEINUMBER+1));
                                dbReference.setValue(whaleLocation);
                            }else if(whalename.equals("Southern Right Whale")){
                                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Southern Right Whale").child(String.valueOf(SiteActivity.RIGHTNUMBER+1));
                                dbReference.setValue(whaleLocation);
                            }else if(whalename.equals("Sperm Whale")){
                                DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Sperm Whale").child(String.valueOf(SiteActivity.SPERMNUMBER+1));
                                dbReference.setValue(whaleLocation);
                            }

                            Intent newIntent = new Intent(SiteSelectionActivity.this,SiteActivity.class);
                            startActivity(newIntent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            map.clear();
                        }
                    });

                    builder.create().show();
                }

            }
        });

    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permission[], @NonNull int[] grantResults){
        locationPermissionGranted = false;
        switch (requestCode){
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION : {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }

        updateLocationUI();
    }

    private void getDeviceLocation(){
        try{
            if(locationPermissionGranted){
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if(task.isSuccessful()){
                            lastKnownLocation = task.getResult();
                            if(lastKnownLocation==null){
                                getLocationPermission();
                            }else{
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()), DEFUALT_ZOOM));
                            }
                        } else{
                            Log.i("Error", "Null location, default will be used");
                            Log.i("Exception", "Exception:"+task.getException().toString());
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, DEFUALT_ZOOM));
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }

        } catch (SecurityException e){
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void updateLocationUI(){
        if(map == null){
            return;
        }
        try{
            if(locationPermissionGranted){
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            }
            else{
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();

            }
        } catch (SecurityException sexExp){
            Log.i("Exception","Exception:"+sexExp.getMessage());
        }
    }

    public boolean checkLocationInTheSea(LatLng latLng){
        Geocoder geocoder;
        List<Address> addresses = new ArrayList<Address>();
        geocoder = new Geocoder(SiteSelectionActivity.this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(addresses.size()==0){
            return true;
        }
        else{
            return false;
        }


    }

    public void showADialog(String title,String information){
        AlertDialog.Builder dialogInfo = new AlertDialog.Builder(SiteSelectionActivity.this);
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


}

