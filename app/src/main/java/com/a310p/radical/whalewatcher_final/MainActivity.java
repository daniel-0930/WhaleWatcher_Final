package com.a310p.radical.whalewatcher_final;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a310p.radical.whalewatcher_final.Models.Whale;
import com.a310p.radical.whalewatcher_final.Models.WhaleLocation;
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
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private CameraPosition cameraPosition;

    private GeoDataClient geoDataClient;
    private PlaceDetectionClient placeDetectionClient;

    private ImageButton resetButton;
    private FusedLocationProviderClient fusedLocationProviderClient;

    private final LatLng defaultLocation = new LatLng(-37.8840,145.0266);
    private static final int DEFUALT_ZOOM = 3;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private boolean locationPermissionGranted;

    private Location lastKnownLocation;

    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    public static ArrayList<WhaleLocation> blueList;
    public static ArrayList<WhaleLocation> southernRightList;
    public static ArrayList<WhaleLocation> finWhaleList;
    public static ArrayList<WhaleLocation> seiWhaleList;
    public static ArrayList<WhaleLocation> humpbackList;
    public static ArrayList<WhaleLocation> spermList;
    public static ArrayList<WhaleLocation> minkeList;
    public static ArrayList<WhaleLocation> killerList;
    public static ArrayList<WhaleLocation> otherList;
    public static ArrayList<WhaleLocation> unknownList;

    private ArrayList<LatLng> locationList;

    private LatLng latLng;


    public static int BLUENUMBER ;
    public static int RIGHTNUMBER ;
    public static int FINNUMBER ;
    public static int SEINUMBER ;
    public static int HUMPBAKCNUMBER ;
    public static int SPERMNUMBER ;
    public static int MINKENUMBER ;
    public static int KILLERNUMBER ;
    public static int OTHERNUMBER;
    public static int UNKNOWNNUMBER;

    private ArrayList<WhaleLocation> whaleLocations = new ArrayList<>();
    private int index;

    private BoomMenuButton bmb;

    private ProgressDialog progressDialog;

    private CircleButton nearestButton;


    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.actionbar_custom_view);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        resetButton = (ImageButton)findViewById(R.id.resetButton);
        nearestButton = (CircleButton)findViewById(R.id.nearestButton);


        blueList = new ArrayList<>();
        southernRightList = new ArrayList<>();
        finWhaleList= new ArrayList<>();
        seiWhaleList= new ArrayList<>();
        humpbackList= new ArrayList<>();
        spermList= new ArrayList<>();
        minkeList= new ArrayList<>();
        killerList= new ArrayList<>();
        otherList = new ArrayList<>();
        unknownList = new ArrayList<>();
        latLng = new LatLng(0,0);

        BLUENUMBER = 0;
        RIGHTNUMBER = 0 ;
        FINNUMBER = 0 ;
        SEINUMBER = 0 ;
        HUMPBAKCNUMBER = 0;
        SPERMNUMBER = 0;
        MINKENUMBER = 0;
        KILLERNUMBER= 0 ;
        OTHERNUMBER = 0;
        UNKNOWNNUMBER = 0;




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
        otherList = addLocationToMap("Other");
        unknownList = addLocationToMap("Unknown");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Leading to the nearest site");
        progressDialog.setMessage("Calculating Distance...");

        index = 0;

        locationList = new ArrayList<>();
        locationList.add(new LatLng(-38.3616115, 141.6261333));
        locationList.add(new LatLng(-38.698863, 143.793045));
        locationList.add(new LatLng(-38.403933, 142.521118));
        locationList.add(new LatLng(-38.856656, 143.511942));
        locationList.add(new LatLng(-38.6129246,142.9539751));
        locationList.add(new LatLng(-38.481405, 145.202489));


        //Navigation menu
        bmb = (BoomMenuButton)findViewById(R.id.bmb);
        assert bmb != null;

        for(int i = 0; i< bmb.getPiecePlaceEnum().pieceNumber(); i++){

            bmb.addBuilder(new TextOutsideCircleButton.Builder()
                    .normalImageRes(BuilderManager.getImageResource())  //set Images
                    .normalTextRes(BuilderManager.getTextResource()).listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Intent i = new Intent();
                            switch(index){
                                case 0:
                                    i = new Intent(MainActivity.this,SelectionActivity.class);
                                    startActivity(i);
                                    break;
                                case 1:
                                    i = new Intent(MainActivity.this,ListWhaleActivity.class);
                                    startActivity(i);
                                    break;
                                case 2:
                                    i = new Intent(MainActivity.this,ReportActivity.class);
                                    startActivity(i);
                                    break;
                                case 3:
                                    i = new Intent(MainActivity.this,TwitterActivity.class);
                                    startActivity(i);
                                    break;
                                case 4:
                                    i = new Intent(MainActivity.this,Agency2Activity.class);
                                    startActivity(i);
                                    break;
                                case 5:
                                    i = new Intent(MainActivity.this,AboutUsActivity.class);
                                    startActivity(i);
                                    break;

                            }

                        }

                    }));

            bmb.setAutoBoom(true);

            nearestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDeviceLocation();
                    new AsyncTask<String, Void, ArrayList<String>>() {
                        @Override
                        protected void onPreExecute() {
                            progressDialog.show();
                        }

                        @Override
                        protected ArrayList<String> doInBackground(String... strings) {
                            ArrayList<String> results = new ArrayList<String>();
                            for(LatLng l : locationList){
                                String result = RESTClient.distanceDetection(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()),l);
                                results.add(result);
                            }
                            return results;
                        }
                        @Override
                        protected void onPostExecute(ArrayList<String> result){
                            JSONObject jsonObject = new JSONObject();

                            ArrayList<Integer> distanceLength = new ArrayList<Integer>();
                            try{
                                for(String s: result){
                                    jsonObject = new JSONObject(s);

                                    JSONArray array = jsonObject.getJSONArray("rows");

                                    JSONObject routes = array.getJSONObject(0);

                                    JSONArray elements = routes.getJSONArray("elements");

                                    JSONObject steps = elements.getJSONObject(0);

                                    JSONObject distance = steps.getJSONObject("distance");
                                    int lengthi = distance.getInt("value");
                                    Log.i("Thisapp",String.valueOf(lengthi));
                                    distanceLength.add(lengthi);
                                }

                                int lengthValueTop = distanceLength.get(0);
                                for(int i =1;i<distanceLength.size();i++){

                                    int lengthValue = distanceLength.get(i);

                                    if(lengthValue<lengthValueTop){
                                        lengthValueTop = lengthValue;
                                        latLng=locationList.get(i);
                                        index = i;
                                    }
                                }

                                new AsyncTask<String, Void, String>() {
                                    @Override
                                    protected String doInBackground(String... strings) {
                                        String result = RESTClient.distanceDirection(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()),latLng);
                                        return result;
                                    }

                                    @Override
                                    protected void onPostExecute(String s) {
                                        JSONObject jsonObject = new JSONObject();
                                        try{
                                            jsonObject = new JSONObject(s);

                                            JSONArray array = jsonObject.getJSONArray("routes");

                                            JSONObject routes = array.getJSONObject(0);

                                            JSONObject steps = routes.getJSONObject("overview_polyline");

                                            String overview = steps.getString("points");

                                            List<LatLng> points = decode(overview);
                                            map.clear();
                                            boolean reload = false;

                                            if(index == 0){
                                                map.addMarker(new MarkerOptions().position(locationList.get(0)).
                                                        title("Pivots Beach-Portland").snippet("http://www.portlandaccom.com.au/gallery/Nuns_Beach_Portland.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);

                                            } else if(index == 1){
                                                map.addMarker(new MarkerOptions().position(locationList.get(1)).
                                                        title("Wongarra, Onion Bay").snippet("http://www.stephaniesonionbay.com/uploads/images/images/StephaniesGreatOceanRoad.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);

                                            }else if(index == 2){
                                                map.addMarker(new MarkerOptions().position(locationList.get(2)).
                                                        title("Logans Beach, Warrnambool").snippet("http://www.australiaforeveryone.com.au/vic/images/Warrnambool_Logans.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);

                                            }else if(index == 3){
                                                map.addMarker(new MarkerOptions().position(locationList.get(3)).
                                                        title("Cape Otway Lightstation").snippet("https://www.visitgreatoceanroad.org.au/assets-regional/Uploads/cape-otway-light-house-570x401.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);

                                            } else if(index == 4){
                                                map.addMarker(new MarkerOptions().position(locationList.get(4)).
                                                        title("Campbell Bay").snippet("https://media-cdn.tripadvisor.com/media/photo-s/0b/d5/4b/0b/port-campbell-bay-on.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);

                                            } else if (index == 5){
                                                map.addMarker(new MarkerOptions().position(locationList.get(5)).
                                                        title("Philipe Island").snippet("http://cd.visitmelbourne.com/-/media/images/phillip-island/things-to-do/nature-and-wildlife/beaches-and-coastlines/ycw-beach_pi_r_1461139_1150x863.jpg?ts=20150828430241&w=480&h=360&crop=1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);

                                            }

                                            map.addPolyline(new PolylineOptions().addAll(points).width(15)
                                                    .color(Color.BLUE)
                                                    .geodesic(true));
                                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()), 7));


                                        }catch(Exception e){

                                        }

                                        progressDialog.dismiss();

                                    }
                                }.execute();

                            }catch (Exception ex){

                            }

                        }
                    }.execute();
                }
            });

            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    map.clear();
                    boolean reload = false;
                    map.addMarker(new MarkerOptions().position(locationList.get(0)).
                            title("Pivots Beach-Portland").snippet("http://www.portlandaccom.com.au/gallery/Nuns_Beach_Portland.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
                    map.addMarker(new MarkerOptions().position(locationList.get(1)).
                            title("Wongarra, Onion Bay").snippet("http://www.stephaniesonionbay.com/uploads/images/images/StephaniesGreatOceanRoad.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
                    map.addMarker(new MarkerOptions().position(locationList.get(2)).
                            title("Logans Beach, Warrnambool").snippet("http://www.australiaforeveryone.com.au/vic/images/Warrnambool_Logans.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
                    map.addMarker(new MarkerOptions().position(locationList.get(3)).
                            title("Cape Otway Lightstation").snippet("https://www.visitgreatoceanroad.org.au/assets-regional/Uploads/cape-otway-light-house-570x401.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
                    map.addMarker(new MarkerOptions().position(locationList.get(4)).
                            title("Campbell Bay").snippet("https://media-cdn.tripadvisor.com/media/photo-s/0b/d5/4b/0b/port-campbell-bay-on.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
                    map.addMarker(new MarkerOptions().position(locationList.get(5)).
                            title("Philipe Island").snippet("http://cd.visitmelbourne.com/-/media/images/phillip-island/things-to-do/nature-and-wildlife/beaches-and-coastlines/ycw-beach_pi_r_1461139_1150x863.jpg?ts=20150828430241&w=480&h=360&crop=1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);

                }
            });

        }


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
            public View getInfoContents(final Marker marker) {
                View inforWindow = getLayoutInflater().inflate(R.layout.custom_info_contents2,(FrameLayout)findViewById(R.id.mapFragment),false);

                inforWindow.setLayoutParams(new RelativeLayout.LayoutParams(600, RelativeLayout.LayoutParams.WRAP_CONTENT));
                TextView title = ((TextView)inforWindow.findViewById(R.id.titleText2));
                title.setText(marker.getTitle());

                ImageView placeImage = ((ImageView)inforWindow.findViewById(R.id.placeImage));
                String url = marker.getSnippet();
                boolean di = ((Boolean)marker.getTag()).booleanValue();
                if(!di){
                    Picasso.with(getApplicationContext()).load(url).into(placeImage,new MarkerCallback(marker));
                } else{
                    Picasso.with(getApplicationContext()).load(url).into(placeImage);
                }



                return inforWindow;
            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                final LatLng markerLatlog = marker.getPosition();
                new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String result = RESTClient.distanceDirection(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()),markerLatlog);
                        return result;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        JSONObject jsonObject = new JSONObject();
                        try{
                            jsonObject = new JSONObject(s);

                            JSONArray array = jsonObject.getJSONArray("routes");

                            JSONObject routes = array.getJSONObject(0);

                            JSONObject steps = routes.getJSONObject("overview_polyline");

                            String overview = steps.getString("points");

                            List<LatLng> points = decode(overview);
                            map.clear();
                            boolean reload = false;
                            map.addMarker(new MarkerOptions().position(marker.getPosition()).
                                    title(marker.getTitle()).snippet(marker.getSnippet()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
                            map.addPolyline(new PolylineOptions().addAll(points).width(15)
                                    .color(Color.BLUE)
                                    .geodesic(true));
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()), 7));



                        }catch(Exception e){

                        }

                        progressDialog.dismiss();

                    }
                }.execute();

                return;
            }
        });


        getLocationPermission();

        updateLocationUI();
        boolean reload = false;
        map.addMarker(new MarkerOptions().position(locationList.get(0)).
                title("Pivots Beach-Portland").snippet("http://nnimgt-a.akamaihd.net/transform/v1/crop/frm/379mw9XPZ7UFRqmwjWhGKkr/bf9cdd1e-c77d-4f40-87ec-666219ec3a85.jpg/r0_0_640_360_w1200_h678_fmax.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
        map.addMarker(new MarkerOptions().position(locationList.get(1)).
                title("Wongarra, Onion Bay").snippet("http://www.stephaniesonionbay.com/uploads/images/images/StephaniesGreatOceanRoad.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
        map.addMarker(new MarkerOptions().position(locationList.get(2)).
                title("Logans Beach, Warrnambool").snippet("http://www.australiaforeveryone.com.au/vic/images/Warrnambool_Logans.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
        map.addMarker(new MarkerOptions().position(locationList.get(3)).
                title("Cape Otway Lightstation").snippet("https://www.visitgreatoceanroad.org.au/assets-regional/Uploads/cape-otway-light-house-570x401.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
        map.addMarker(new MarkerOptions().position(locationList.get(4)).
                title("Campbell Bay").snippet("https://media-cdn.tripadvisor.com/media/photo-s/0b/d5/4b/0b/port-campbell-bay-on.jpg").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);
        map.addMarker(new MarkerOptions().position(locationList.get(5)).
                title("Philipe Island").snippet("http://cd.visitmelbourne.com/-/media/images/phillip-island/things-to-do/nature-and-wildlife/beaches-and-coastlines/ycw-beach_pi_r_1461139_1150x863.jpg?ts=20150828430241&w=480&h=360&crop=1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))).setTag(reload);

        //show ways to go to there will use this one
        getDeviceLocation();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-36.686043, 143.580322), 6));

    }


    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
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
                                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()), DEFUALT_ZOOM));
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
//                    LatLng thisLocation = new LatLng(w.getLatitude(),w.getLongitude());
//                    String[] timeset = w.getTime().split("-");
//                    String timeString = timeset[0]+"/"+timeset[1]+"/"+timeset[2]+" "+timeset[3]+":"+timeset[4]+":"+timeset[5];
////                    map.addMarker(new MarkerOptions().position(thisLocation).
////                            title(whalename).snippet(timeString).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


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
                } else if(whalename.equals("Other")){
                    OTHERNUMBER = otherList.size();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return whaleLocations;

    }

    private List<LatLng> decode(String encodedPath){
        int len = encodedPath.length();

        final List<LatLng> path = new ArrayList<LatLng>(len / 2);
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new LatLng(lat * 1e-5, lng * 1e-5));
        }

        return path;

    }
//

    private class MarkerCallback implements Callback {
        Marker marker=null;

        MarkerCallback(Marker marker) {
            this.marker=marker;
        }

        @Override
        public void onError() {
            Log.e(getClass().getSimpleName(), "Error loading thumbnail!");
        }

        @Override
        public void onSuccess() {
            boolean reload = true;
            marker.setTag(reload);
            marker.showInfoWindow();
        }
    }


}
