package com.a310p.radical.whalewatcher_final;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by zhangzeyao on 20/9/17.
 */

public class RESTClient {

    public static String searchWhaleNews() {


        //Need latitude and longtitude of current place;
        final String path = "https://api.twitter.com/1.1/search/tweets.json?q=saw%20a%20whale&src=typd&oauth_consumer_key=foX1V1dpGKUDz6MWBGKZNGKga";
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        try {
            url = new URL(path);

            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Log.i("Myapp", "Success2");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.i("Myapp", "Success3");
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            textResult = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        } finally {
            conn.disconnect();
        }

        return textResult;
    }

    public static String distanceDetection(LatLng latLng,LatLng latLngdest){

        final String path = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+String.valueOf(latLng.latitude)+","+String.valueOf(latLng.longitude)+"&destinations=" +
                String.valueOf(latLngdest.latitude)+"%2C"+String.valueOf(latLngdest.longitude)+
                "&key=AIzaSyAUw_5rDmAEPn0cMEfbEfSL4_hbOn73KeM";
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        try {
            url = new URL(path);

            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Log.i("Myapp", "Success2");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.i("Myapp", "Success3");
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            textResult = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        } finally {
            conn.disconnect();
        }

        return textResult;
    }
    public static String distanceDirection(LatLng latLng,LatLng latLngdest){

        final String path = "https://maps.googleapis.com/maps/api/directions/json?origin="+String.valueOf(latLng.latitude)+","+String.valueOf(latLng.longitude)+"&destination=" +
                String.valueOf(latLngdest.latitude)+","+String.valueOf(latLngdest.longitude)+
                "&key=AIzaSyAbAgSV8bhDJRw5PRJLKchwsawzw_Hgqss";
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        try {
            url = new URL(path);

            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Log.i("Myapp", "Success2");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.i("Myapp", "Success3");
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            textResult = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        } finally {
            conn.disconnect();
        }

        return textResult;
    }
}
