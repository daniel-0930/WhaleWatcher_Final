package com.a310p.radical.whalewatcher_final;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.nfc.FormatException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a310p.radical.whalewatcher_final.Models.Whale;
import com.a310p.radical.whalewatcher_final.Models.WhaleLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.view.MenuItem;


public class SiteActivity extends AppCompatActivity implements OnMapReadyCallback{

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

    private ArrayList<WhaleLocation> blueList;
    private ArrayList<WhaleLocation> southernRightList;
    private ArrayList<WhaleLocation> finWhaleList;
    private ArrayList<WhaleLocation> seiWhaleList;
    private ArrayList<WhaleLocation> humpbackList;
    private ArrayList<WhaleLocation> spermList;
    private ArrayList<WhaleLocation> minkeList;
    private ArrayList<WhaleLocation> killerList;

    public static int BLUENUMBER ;
    public static int RIGHTNUMBER ;
    public static int FINNUMBER ;
    public static int SEINUMBER ;
    public static int HUMPBAKCNUMBER ;
    public static int SPERMNUMBER ;
    public static int MINKENUMBER ;
    public static int KILLERNUMBER ;

    private ArrayList<WhaleLocation> whaleLocations = new ArrayList<>();


    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        blueList = new ArrayList<>();
        southernRightList = new ArrayList<>();
        finWhaleList= new ArrayList<>();
        seiWhaleList= new ArrayList<>();
        humpbackList= new ArrayList<>();
        spermList= new ArrayList<>();
        minkeList= new ArrayList<>();
        killerList= new ArrayList<>();

        BLUENUMBER = 0;
        RIGHTNUMBER = 0 ;
        FINNUMBER = 0 ;
        SEINUMBER = 0 ;
        HUMPBAKCNUMBER = 0;
        SPERMNUMBER = 0;
        MINKENUMBER = 0;
        KILLERNUMBER= 0 ;


        setContentView(R.layout.activity_site);
        geoDataClient = Places.getGeoDataClient(this,null);
        placeDetectionClient = Places.getPlaceDetectionClient(this,null);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        blueList = addLocationToMap("Blue Whale");
        southernRightList = addLocationToMap("Southern Right Whale");
        finWhaleList = addLocationToMap("Fin Whale");
        seiWhaleList = addLocationToMap("Sei Whale");
        humpbackList = addLocationToMap("Humpback Whale");
        spermList = addLocationToMap("Sperm Whale");
        minkeList = addLocationToMap("Minke Whale");
        killerList = addLocationToMap("Killer Whale");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_site_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.last_week){
            selectLastWeekAll();
        } else if(item.getItemId() == R.id.last_month){
            selectLastMonthAll();
        } else if(item.getItemId()== R.id.this_year){
            selectThisYearAll();
        } else if(item.getItemId() == R.id.report_site){
            Intent reportIntent = new Intent(SiteActivity.this,SelectionActivity.class);
            startActivity(reportIntent);
        } else if(item.getItemId() == R.id.reset){
            selectAll();
        }
        return true;
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
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View inforWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,(FrameLayout)findViewById(R.id.mapFragment),false);

                TextView title = ((TextView)inforWindow.findViewById(R.id.titleText));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView)inforWindow.findViewById(R.id.spinnetText));
                snippet.setText(marker.getSnippet());

                return inforWindow;
            }
        });

        getLocationPermission();

        updateLocationUI();

        getDeviceLocation();




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
    public void onRequestPermissionsResult(int requestCode, @NonNull String permission[],@NonNull int[] grantResults){
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


    public ArrayList<WhaleLocation> addLocationToMap(final String whalename){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(whalename);
        final ArrayList<WhaleLocation> whaleLocations = new ArrayList<>();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot whaleSnapshot : dataSnapshot.getChildren()){

                    WhaleLocation w = whaleSnapshot.getValue(WhaleLocation.class);
                    whaleLocations.add(w);
                    LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                    String[] timeset = w.getTime().split("-");
                    String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                    map.addMarker(new MarkerOptions().position(thisLocation).
                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                }
                if(whalename.equals("Blue Whale")){
                    BLUENUMBER = blueList.size();
                } else if(whalename.equals("Southern Right Whale")){
                    RIGHTNUMBER = southernRightList.size();
                } else if(whalename.equals("Fin Whale")){
                    FINNUMBER = finWhaleList.size() ;
                } else if(whalename.equals("Sei Whale")){
                    SEINUMBER = seiWhaleList.size() ;

                } else if(whalename.equals("Humpback Whale")){
                    HUMPBAKCNUMBER = humpbackList.size();
                } else if(whalename.equals("Sperm Whale")){
                    SPERMNUMBER = spermList.size();
                } else if(whalename.equals("Minke Whale")){
                    MINKENUMBER = minkeList.size();
                } else if(whalename.equals("Killer Whale")){
                    KILLERNUMBER= killerList.size() ;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return whaleLocations;

    }


    public void selectLastWeekAll(){
        map.clear();
        selectLastWeekSingle(blueList,"Blue Whale");
        selectLastWeekSingle(southernRightList,"Southern Right Whale");
        selectLastWeekSingle(finWhaleList,"Fin Whale");
        selectLastWeekSingle(seiWhaleList,"Sei Whale");
        selectLastWeekSingle(humpbackList,"Humpack Whale");
        selectLastWeekSingle(spermList,"Sperm Whale");
        selectLastWeekSingle(minkeList,"Minke Whale");
        selectLastWeekSingle(killerList,"Killer Whale");
    }

    public void selectLastWeekSingle(ArrayList<WhaleLocation> locationList,String whalename){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int week = c.get(Calendar.WEEK_OF_YEAR);

        Calendar c2 = new GregorianCalendar(Locale.getDefault());
        if(locationList.size() !=0){
            for(WhaleLocation w: locationList){
                String[] thisTime= w.getTime().split("-");
                try {
                    Date datetime = new SimpleDateFormat("dd/MM/yyyy").parse(thisTime[2] + "/" + thisTime[1] + "/" + thisTime[0]);
                    if(week==1){
                        int weekDay = c.getFirstDayOfWeek();
                        if(weekDay == 1){
                            Date endate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/" + String.valueOf(year-1));
                            Date startdate = new SimpleDateFormat("dd/MM/yyyy").parse("25/12/" + String.valueOf(year-1));
                            if(datetime.after(startdate) && datetime.before(endate)){
                                LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                                String[] timeset = w.getTime().split("-");
                                String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                                map.addMarker(new MarkerOptions().position(thisLocation).
                                        title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                            }
                        } else{
                            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(weekDay)+"/1/" + String.valueOf(year));
                            Date startDate = new Date(endDate.getTime() - 604800000L);
                            if(datetime.after(startDate) && datetime.before(endDate)){
                                LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                                String[] timeset = w.getTime().split("-");
                                String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                                map.addMarker(new MarkerOptions().position(thisLocation).
                                        title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                            }

                        }


                    }else{
                        c2.set(Calendar.WEEK_OF_YEAR,week-1);
                        c2.set(Calendar.YEAR,year);

                        int firstDayofWeek = c2.getFirstDayOfWeek();
                        c2.set(Calendar.DAY_OF_WEEK, firstDayofWeek);
                        Date firstDay = c2.getTime();

                        Date lastDay = new Date(firstDay.getTime() + 604800000L);
                        if(datetime.after(firstDay) && datetime.before(lastDay)){
                            LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                            String[] timeset = w.getTime().split("-");
                            String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                            map.addMarker(new MarkerOptions().position(thisLocation).
                                    title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        }

                    }
                }catch (ParseException ex){
                    Log.i("Error",ex.getMessage());
                }

            }
        }
    }

    public void selectLastMonthAll(){
        map.clear();
        selectLastMonthSingle(blueList,"Blue Whale");
        selectLastMonthSingle(southernRightList,"Southern Right Whale");
        selectLastMonthSingle(finWhaleList,"Fin Whale");
        selectLastMonthSingle(seiWhaleList,"Sei Whale");
        selectLastMonthSingle(humpbackList,"Humpack Whale");
        selectLastMonthSingle(spermList,"Sperm Whale");
        selectLastMonthSingle(minkeList,"Minke Whale");
        selectLastMonthSingle(killerList,"Killer Whale");
    }

    public void selectLastMonthSingle(ArrayList<WhaleLocation> locationList,String whalename){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        if(locationList.size()!=0){
            for(WhaleLocation w: locationList){
                if(month == 1){
                    if(w.getTime().contains(String.valueOf(year-1)+"-"+String.valueOf(12))){
                        LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                        String[] timeset = w.getTime().split("-");
                        String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                        map.addMarker(new MarkerOptions().position(thisLocation).
                                title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    }

                }else{
                    if(w.getTime().contains(String.valueOf(year)+"-"+String.valueOf(month-1))){
                        LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                        String[] timeset = w.getTime().split("-");
                        String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                        map.addMarker(new MarkerOptions().position(thisLocation).
                                title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    }
                }

            }
        }
    }







    public void selectThisYearAll(){
        map.clear();
        selectThisYearSingle(blueList,"Blue Whale");
        selectThisYearSingle(southernRightList,"Southern Right Whale");
        selectThisYearSingle(finWhaleList,"Fin Whale");
        selectThisYearSingle(seiWhaleList,"Sei Whale");
        selectThisYearSingle(humpbackList,"Humpack Whale");
        selectThisYearSingle(spermList,"Sperm Whale");
        selectThisYearSingle(minkeList,"Minke Whale");
        selectThisYearSingle(killerList,"Killer Whale");
    }

    public void selectThisYearSingle(ArrayList<WhaleLocation> locationList,String whalename){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        if(locationList.size()!=0){
            for(WhaleLocation w: locationList){
                if(w.getTime().contains(String.valueOf(year))){
                    LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                    String[] timeset = w.getTime().split("-");
                    String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                    map.addMarker(new MarkerOptions().position(thisLocation).
                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
            }
        }
    }

    public void selectAll(){
        map.clear();
        selectAllSingle(blueList,"Blue Whale");
        selectAllSingle(southernRightList,"Southern Right Whale");
        selectAllSingle(finWhaleList,"Fin Whale");
        selectAllSingle(seiWhaleList,"Sei Whale");
        selectAllSingle(humpbackList,"Humpack Whale");
        selectAllSingle(spermList,"Sperm Whale");
        selectAllSingle(minkeList,"Minke Whale");
        selectAllSingle(killerList,"Killer Whale");
    }

    public void selectAllSingle(ArrayList<WhaleLocation> locationList,String whalename){
        if(locationList.size()!=0){
            for(WhaleLocation w: locationList){
                    LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                String[] timeset = w.getTime().split("-");
                String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                    map.addMarker(new MarkerOptions().position(thisLocation).
                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            }
        }
    }

}
