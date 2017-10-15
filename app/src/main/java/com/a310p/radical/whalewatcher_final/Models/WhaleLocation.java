package com.a310p.radical.whalewatcher_final.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Agency model to used as a tour agency object
 * version 1.0
 * @author Zeyao Zhang
 * @since 2/9/2017
 */

public class WhaleLocation implements Parcelable{

    //Declare the field of whale's location
    private String time;
    private double latitude;
    private double longitude;

    /**
     * Default constructor
     * */
    public WhaleLocation() {
    }

    /**
     * Constructor with all field
     * @param latitude latitude of the whale's location
     * @param longitude longitude of the whale's location
     * @param time the time user saw the whale
     * */
    public WhaleLocation(String time, double latitude, double longitude) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * The parcelable object to create in constructor
     * @param in the parcelable object
     * */
    protected WhaleLocation(Parcel in){
        time = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    //Creator of whale in parcelable
    public static final Creator<WhaleLocation> CREATOR = new Creator<WhaleLocation>() {
        @Override
        public WhaleLocation createFromParcel(Parcel in) {
            return new WhaleLocation(in);
        }

        @Override
        public WhaleLocation[] newArray(int size) {
            return new WhaleLocation[size];
        }
    };

    /**
     * Get the time of whale user report
     * @return String the time user report
     * */
    public String getTime() {
        return time;
    }

    /**
     * Set the time of whale user report
     * @param time the time user report
     * */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get the time of whale user report
     * @return String the time user report
     * */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the latitude of whale user report
     * @param latitude the latitude of user reporting
     * */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the time of whale user report
     * @return String the time user report
     * */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the longitude of whale user report
     * @param longitude the longitude user report
     * */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(time);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }
}
