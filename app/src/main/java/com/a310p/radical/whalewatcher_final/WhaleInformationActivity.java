package com.a310p.radical.whalewatcher_final;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.a310p.radical.whalewatcher_final.Models.Whale;
import com.a310p.radical.whalewatcher_final.Models.WhaleLocation;
import com.andexert.expandablelayout.library.ExpandableLayout;
import com.bumptech.glide.Glide;
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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class WhaleInformationActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap map;
    private CameraPosition cameraPosition;

    private GeoDataClient geoDataClient;
    private PlaceDetectionClient placeDetectionClient;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private Whale whale;
    private ImageView whalePic;
    private TextView nameText;
    private TextView lengthText;
    private TextView siteText;
    private TextView monthText;
    private TextView colorText;
    private TextView descripText;
    private TextView habitatText;
    private TextView scNameText;

    private Spinner spinner;

    private ExpandableLayout nameExpandable,lengthExpandable,siteExpandable,monthExpandable,colorExpandable,descripExpandable,habitatExpandable,scnameExpandable;
    private ImageButton nameParentButton,lengthParentButton,siteParentButton,monthParentButton,colorParentButton,descripParentButton,habitatParentButton,scnameParentButton;



    private ArrayList<WhaleLocation> whaleLocationArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whale_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.whaleInformationToolbar);
        setSupportActionBar(toolbar);

        Intent whaleIntent = getIntent();
        whale = whaleIntent.getParcelableExtra("whale");

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(whale.getName());
        }


        whaleLocationArrayList = new ArrayList<>();
        if(whale.getName().equals("Blue Whale")){
            whaleLocationArrayList = MainActivity.blueList;
        } else if(whale.getName().equals("Southern Right Whale")){
            whaleLocationArrayList = MainActivity.southernRightList;
        }else if(whale.getName().equals("Fin Whale")){
            whaleLocationArrayList = MainActivity.finWhaleList;
        }else if(whale.getName().equals("Sei Whale")){
            whaleLocationArrayList = MainActivity.seiWhaleList;
        }else if(whale.getName().equals("Humpback Whale")){
            whaleLocationArrayList = MainActivity.humpbackList;
        }else if(whale.getName().equals("Sperm Whale")){
            whaleLocationArrayList = MainActivity.spermList;
        }else if(whale.getName().equals("Minke Whale")){
            whaleLocationArrayList = MainActivity.minkeList;
        }else if(whale.getName().equals("Killer Whale")){
            whaleLocationArrayList = MainActivity.killerList;
        }else if(whale.getName().equals("Other Whale")){
            whaleLocationArrayList = MainActivity.otherList;
        } else if(whale.getName().equals("Unknown")){
            whaleLocationArrayList = MainActivity.otherList;
        }




        whalePic = (ImageView)findViewById(R.id.whalePic);
        nameText = (TextView)findViewById(R.id.nameText);
        lengthText = (TextView)findViewById(R.id.lengthText);
        siteText = (TextView)findViewById(R.id.siteText);
        monthText = (TextView)findViewById(R.id.monthText);
        colorText = (TextView)findViewById(R.id.colorText);
        descripText = (TextView)findViewById(R.id.descripText);
        habitatText = (TextView)findViewById(R.id.habitatText);
        scNameText = (TextView)findViewById(R.id.scNameText);
        spinner = (Spinner)findViewById(R.id.timeInfoSpinner);






        Glide.with(this).load(whale.getUrl()).into(whalePic);
        nameText.setText(whale.getName());
        String lengthtext = "";
        if(whale.getWeight()>=10000){
            lengthtext = String.valueOf(whale.getLength()) + " meters and "+ String.valueOf(whale.getWeight()/1000)+" tons";
        }
        else{
            lengthtext = String.valueOf(whale.getLength()) + " meters and "+ String.valueOf(whale.getWeight())+" KG";
        }
        lengthText.setText(lengthtext);
        siteText.setText(whale.getPossibleSite());
        monthText.setText(whale.getPossibleMonth());
        colorText.setText(whale.getFeature());
        descripText.setText(whale.getDescription());
        habitatText.setText(whale.getHabitat());
        scNameText.setText(whale.getScientificName());

        geoDataClient = Places.getGeoDataClient(this,null);
        placeDetectionClient = Places.getPlaceDetectionClient(this,null);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapInformationFragment);
        mapFragment.getMapAsync(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                showOnMap(whale.getName(),spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        nameParentButton = (ImageButton)findViewById(R.id.nameParentButton);
        nameExpandable = (ExpandableLayout)findViewById(R.id.nameExpandable);
        modifyButtonOnExpandable(nameExpandable,nameParentButton);

        lengthParentButton = (ImageButton)findViewById(R.id.lengthParentButton);
        lengthExpandable = (ExpandableLayout)findViewById(R.id.lengthExpandable);
        modifyButtonOnExpandable(lengthExpandable,lengthParentButton);

        siteParentButton = (ImageButton)findViewById(R.id.possiteParentButton);
        siteExpandable = (ExpandableLayout)findViewById(R.id.possiteExpandable);
        modifyButtonOnExpandable(siteExpandable,siteParentButton);

        monthParentButton = (ImageButton)findViewById(R.id.posmonthParentButton);
        monthExpandable = (ExpandableLayout)findViewById(R.id.posmonthExpandable);
        modifyButtonOnExpandable(monthExpandable,monthParentButton);

        colorParentButton = (ImageButton)findViewById(R.id.colParentButton);
        colorExpandable = (ExpandableLayout)findViewById(R.id.colExpandable);
        modifyButtonOnExpandable(colorExpandable,colorParentButton);

        descripParentButton = (ImageButton)findViewById(R.id.discripParentButton);
        descripExpandable = (ExpandableLayout)findViewById(R.id.descripExpandable);
        modifyButtonOnExpandable(descripExpandable,descripParentButton);

        habitatParentButton = (ImageButton)findViewById(R.id.habitatParentButton);
        habitatExpandable = (ExpandableLayout)findViewById(R.id.habitatExpandable);
        modifyButtonOnExpandable(habitatExpandable,habitatParentButton);

        scnameParentButton = (ImageButton)findViewById(R.id.scientificParentButton);
        scnameExpandable = (ExpandableLayout)findViewById(R.id.scientificExpandable);
        modifyButtonOnExpandable(scnameExpandable,scnameParentButton);




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-36.686043, 143.580322), 6));

        map.clear();
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(final Marker marker) {
                View inforWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,(FrameLayout)findViewById(R.id.mapFragment),false);

                TextView title = ((TextView)inforWindow.findViewById(R.id.titleText));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView)inforWindow.findViewById(R.id.spinnetText));
                snippet.setText(marker.getSnippet());

                return inforWindow;
            }
        });

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-36.686043, 143.580322), 6));
        for(WhaleLocation w: whaleLocationArrayList){

            String[] timeset = w.getTime().split("-");
            String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
            map.addMarker(new MarkerOptions().position(new LatLng(w.getLatitude(),w.getLongitude())).
                    title(whale.getName()).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


        }






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
                    Date endDate = c.getTime();
                    Date startDate = new Date(endDate.getTime() - 604800000L);
                    if(datetime.after(startDate) && datetime.before(endDate)){
                        LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                        String[] timeset = w.getTime().split("-");
                        String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                        map.addMarker(new MarkerOptions().position(thisLocation).
                                title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    }
                }catch (ParseException ex){
                    Log.i("Error",ex.getMessage());
                }

            }
        }
    }



    public void selectLastMonthSingle(ArrayList<WhaleLocation> locationList,String whalename){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        if(locationList.size()!=0) {
            for(WhaleLocation w: locationList){
                String[] thisTime= w.getTime().split("-");
                try {
                    Date datetime = new SimpleDateFormat("dd/MM/yyyy").parse(thisTime[2] + "/" + thisTime[1] + "/" + thisTime[0]);
                    Date endDate = c.getTime();
                    Date startDate = new Date(endDate.getTime() - 2592000000L);
                    if(datetime.after(startDate) && datetime.before(endDate)){
                        LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
                        String[] timeset = w.getTime().split("-");
                        String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
                        map.addMarker(new MarkerOptions().position(thisLocation).
                                title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    }
                }catch (ParseException ex){
                    Log.i("Error",ex.getMessage());
                }

            }
        }
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
    private void selectLastSpringSingle(ArrayList<WhaleLocation> locationList, String whalename) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR)-1;

        Calendar c2 = new GregorianCalendar(Locale.getDefault());
        if (locationList.size() != 0) {
            for (WhaleLocation w : locationList) {
                if (w.getTime().contains(String.valueOf(year) + "-9") || w.getTime().contains(String.valueOf(year) + "-10") || w.getTime().contains(String.valueOf(year) + "-11")) {
                    LatLng thisLocation = new LatLng(w.getLatitude(), w.getLongitude());
                    String[] timeset = w.getTime().split("-");
                    String timeString = timeset[0] + "/" + timeset[1] + "/" + timeset[2] + " " + timeset[3] + ":" + timeset[4] + ":" + timeset[5];
                    map.addMarker(new MarkerOptions().position(thisLocation).
                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }


            }
        }
    }


    private void selectLastSummerSingle(ArrayList<WhaleLocation> locationList, String whalename){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR)-1;

        Calendar c2 = new GregorianCalendar(Locale.getDefault());
        if (locationList.size() != 0) {
            for (WhaleLocation w : locationList) {
                if (w.getTime().contains(String.valueOf(year) + "-12") || w.getTime().contains(String.valueOf(year+1) + "-1") || w.getTime().contains(String.valueOf(year+1) + "-2")) {
                    LatLng thisLocation = new LatLng(w.getLatitude(), w.getLongitude());
                    String[] timeset = w.getTime().split("-");
                    String timeString = timeset[0] + "/" + timeset[1] + "/" + timeset[2] + " " + timeset[3] + ":" + timeset[4] + ":" + timeset[5];
                    map.addMarker(new MarkerOptions().position(thisLocation).
                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }


            }
        }
    }
    private void selectLastAutumnSingle(ArrayList<WhaleLocation> locationList, String whalename){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR)-1;

        Calendar c2 = new GregorianCalendar(Locale.getDefault());
        if (locationList.size() != 0) {
            for (WhaleLocation w : locationList) {
                if (w.getTime().contains(String.valueOf(year) + "-6") || w.getTime().contains(String.valueOf(year) + "-7") || w.getTime().contains(String.valueOf(year) + "-8")) {
                    LatLng thisLocation = new LatLng(w.getLatitude(), w.getLongitude());
                    String[] timeset = w.getTime().split("-");
                    String timeString = timeset[0] + "/" + timeset[1] + "/" + timeset[2] + " " + timeset[3] + ":" + timeset[4] + ":" + timeset[5];
                    map.addMarker(new MarkerOptions().position(thisLocation).
                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }


            }
        }
    }
    private void selectLastWinterSingle(ArrayList<WhaleLocation> locationList, String whalename){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR)-1;

        Calendar c2 = new GregorianCalendar(Locale.getDefault());
        if (locationList.size() != 0) {
            for (WhaleLocation w : locationList) {
                if (w.getTime().contains(String.valueOf(year) + "-3") || w.getTime().contains(String.valueOf(year) + "-4") || w.getTime().contains(String.valueOf(year) + "-5")) {
                    LatLng thisLocation = new LatLng(w.getLatitude(), w.getLongitude());
                    String[] timeset = w.getTime().split("-");
                    String timeString = timeset[0] + "/" + timeset[1] + "/" + timeset[2] + " " + timeset[3] + ":" + timeset[4] + ":" + timeset[5];
                    map.addMarker(new MarkerOptions().position(thisLocation).
                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }


            }
        }
    }
    private void selectLastYearSingle(ArrayList<WhaleLocation> locationList, String whalename){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR)-1;

        Calendar c2 = new GregorianCalendar(Locale.getDefault());
        if (locationList.size() != 0) {
            for (WhaleLocation w : locationList) {
                if (w.getTime().contains(String.valueOf(year))) {
                    LatLng thisLocation = new LatLng(w.getLatitude(), w.getLongitude());
                    String[] timeset = w.getTime().split("-");
                    String timeString = timeset[0] + "/" + timeset[1] + "/" + timeset[2] + " " + timeset[3] + ":" + timeset[4] + ":" + timeset[5];
                    map.addMarker(new MarkerOptions().position(thisLocation).
                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }


            }
        }
    }


    private void showOnMap(String whaleType, String time){
        if(time.equals("----------Time Filter----------")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectAllSingle(MainActivity.blueList,"Blue Whale");
            } else if (whaleType.equals("Southern Right Whale")){
                selectAllSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectAllSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectAllSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectAllSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectAllSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectAllSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectAllSingle(MainActivity.minkeList,"Minke Whale");
            }else if(whaleType.equals("Other")){
                selectAllSingle(MainActivity.otherList,"Other");
            }
        } else if(time.equals("Last Spring")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectLastSpringSingle(MainActivity.blueList,"Blue Whale");
            } else if (whaleType.equals("Southern Right Whale")){
                selectLastSpringSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectLastSpringSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectLastSpringSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectLastSpringSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectLastSpringSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectLastSpringSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectLastSpringSingle(MainActivity.minkeList,"Minke Whale");
            }else if(whaleType.equals("Other")){
                selectLastSpringSingle(MainActivity.otherList,"Other");
            }
        }
        else if(time.equals("Last Summer")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectLastSummerSingle(MainActivity.blueList,"Blue Whale");
            } else if (whaleType.equals("Southern Right Whale")){
                selectLastSummerSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectLastSummerSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectLastSummerSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectLastSummerSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectLastSummerSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectLastSummerSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectLastSummerSingle(MainActivity.minkeList,"Minke Whale");
            }else if(whaleType.equals("Other")){
                selectLastSummerSingle(MainActivity.otherList,"Other");
            }
        }
        else if(time.equals("Last Autumn")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectLastAutumnSingle(MainActivity.blueList,"Blue Whale");
            } else if (whaleType.equals("Southern Right Whale")){
                selectLastAutumnSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectLastAutumnSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectLastAutumnSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectLastAutumnSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectLastAutumnSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectLastAutumnSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectLastAutumnSingle(MainActivity.minkeList,"Minke Whale");
            }else if(whaleType.equals("Other")){
                selectLastAutumnSingle(MainActivity.otherList,"Other");
            }
        }
        else if(time.equals("Last Winter")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectLastWinterSingle(MainActivity.blueList,"Blue Whale");
            } else if (whaleType.equals("Southern Right Whale")){
                selectLastWinterSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectLastWinterSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectLastWinterSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectLastWinterSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectLastWinterSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectLastWinterSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectLastWinterSingle(MainActivity.minkeList,"Minke Whale");
            }else if(whaleType.equals("Other")){
                selectLastWinterSingle(MainActivity.otherList,"Other");
            }
        }
        else if(time.equals("Last Year")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectLastYearSingle(MainActivity.blueList,"Blue Whale");
            } else if (whaleType.equals("Southern Right Whale")){
                selectLastYearSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectLastYearSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectLastYearSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectLastYearSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectLastYearSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectLastYearSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectLastYearSingle(MainActivity.minkeList,"Minke Whale");
            }else if(whaleType.equals("Other")){
                selectLastYearSingle(MainActivity.otherList,"Other");
            }
        }
        else if(time.equals("Last Week")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectLastWeekSingle(MainActivity.blueList,"Blue Whale");
            }else if (whaleType.equals("Southern Right Whale")){
                selectLastWeekSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectLastWeekSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectLastWeekSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectLastWeekSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectLastWeekSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectLastWeekSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectLastWeekSingle(MainActivity.minkeList,"Minke Whale");
            }else if(whaleType.equals("Other")){
                selectLastWeekSingle(MainActivity.otherList,"Other");
            }
        }
        else if(time.equals("Last Month")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectLastMonthSingle(MainActivity.blueList,"Blue Whale");
            } else if (whaleType.equals("Southern Right Whale")){
                selectLastMonthSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectLastMonthSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectLastMonthSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectLastMonthSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectLastMonthSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectLastMonthSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectLastMonthSingle(MainActivity.minkeList,"Minke Whale");
            }else if(whaleType.equals("Other")){
                selectLastMonthSingle(MainActivity.otherList,"Other");
            }
        }
        else if(time.equals("This Year")){
            map.clear();
            if(whaleType.equals("Blue Whale")){
                selectThisYearSingle(MainActivity.blueList,"Blue Whale");
            } else if (whaleType.equals("Southern Right Whale")){
                selectThisYearSingle(MainActivity.southernRightList,"Southern Right Whale");
            }else if (whaleType.equals("Fin Whale")){
                selectThisYearSingle(MainActivity.finWhaleList,"Fin Whale");
            }else if (whaleType.equals("Sei Whale")){
                selectThisYearSingle(MainActivity.seiWhaleList,"Sei Whale");
            }else if (whaleType.equals("Humpback Whale")){
                selectThisYearSingle(MainActivity.humpbackList,"Humpack Whale");
            }else if (whaleType.equals("Sperm Whale")){
                selectThisYearSingle(MainActivity.spermList,"Sperm Whale");
            }else if (whaleType.equals("Killer Whale")){
                selectThisYearSingle(MainActivity.killerList,"Killer Whale");
            }else if (whaleType.equals("Minke Whale")){
                selectThisYearSingle(MainActivity.minkeList,"Minke Whale");
            } else if(whaleType.equals("Other")){
                selectThisYearSingle(MainActivity.otherList,"Other");
            }
        }
    }

    private void modifyButtonOnExpandable(final ExpandableLayout expandableLayout,final ImageButton imageButton){
        expandableLayout.getHeaderRelativeLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expandableLayout.isOpened()){
                    expandableLayout.hide();
                    imageButton.setImageResource(R.drawable.ic_more);
                }else{
                    expandableLayout.show();
                    imageButton.setImageResource(R.drawable.ic_less);

                }

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expandableLayout.isOpened()){
                    expandableLayout.hide();
                    imageButton.setImageResource(R.drawable.ic_more);
                }else{
                    expandableLayout.show();
                    imageButton.setImageResource(R.drawable.ic_less);

                }
            }
        });
    }
}
