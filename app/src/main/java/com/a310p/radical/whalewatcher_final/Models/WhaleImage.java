package com.a310p.radical.whalewatcher_final.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangzeyao on 11/9/17.
 */

public class WhaleImage implements Parcelable {

    private String title;


    public WhaleImage(String title) {
        this.title = title;
    }

    protected WhaleImage(Parcel in){
        title = in.readString();
    }

    public static final Creator<WhaleImage> CREATOR = new Creator<WhaleImage>() {
        @Override
        public WhaleImage createFromParcel(Parcel parcel) {
            return new WhaleImage(parcel);
        }

        @Override
        public WhaleImage[] newArray(int i) {
            return new WhaleImage[i];
        }
    };


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
    }
}
