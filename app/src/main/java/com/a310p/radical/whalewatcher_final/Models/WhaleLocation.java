package com.a310p.radical.whalewatcher_final.Models;

/**
 * Created by zhangzeyao on 2/9/17.
 */

public class WhaleLocation {
    private String time;
    private double latitude;
    private double longitude;

    public WhaleLocation() {
    }

    public WhaleLocation(String time, double latitude, double longitude) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
