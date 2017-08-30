package com.a310p.radical.whalewatcher_final;

import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SiteActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{

    private GoogleMap mgoogleMap;
    private Location currentLocation;
    private GoogleApiClient mAPiClient;

    private static final long MAX_UPDATE_INTERVAL = 10000; // 10 seconds
    private static final long MIN_UPDATE_INTERVAL = 2000; // 2 seconds
    private static final LatLng LOCATION_CAULFIELD = new LatLng(-37.8770, 145.0443);

    @Override
    protected void onStart(){
        mAPiClient.connect();;
        super.onStart();
    }

    @Override
    protected void onStop(){
        mAPiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        if(mAPiClient == null){
            mAPiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        }

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_CAULFIELD,15));
        AddDefaultMakers();
    }

    private void AddDefaultMakers(){
        mgoogleMap.addMarker(new MarkerOptions().position(LOCATION_CAULFIELD).title("Monash Caulfield"));
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        changeMapLocation();
    }

    private void changeMapLocation(){
        if(currentLocation != null && mgoogleMap !=null){
            LatLng newPosition = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
            mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newPosition,15));
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try{
            LocationRequest locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setInterval(MAX_UPDATE_INTERVAL).setFastestInterval(MIN_UPDATE_INTERVAL);
            LocationServices.FusedLocationApi.requestLocationUpdates(mAPiClient,locationRequest,this);
        }catch (SecurityException secEx){
            Toast.makeText(this,"ERROR: Please enable location services",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
