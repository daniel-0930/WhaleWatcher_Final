package com.a310p.radical.whalewatcher_final.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangzeyao on 22/8/17.
 */

public class Whale implements Parcelable{

    //
    public static final String TABLE_NAME = "whale";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_SHORTDISCRIP="shortDiscrip";
    public static final String COLUMN_POSSIBLESITE = "possibleSite";
    public static final String COLUMN_POSSIBLEMONTH = "possibleMonth";
    public static final String COLUMN_FEATURE = "feature";
    public static final String COLUMN_SCIENTIFICNAME = "scientificName";
    public static final String COLUMN_HABITAT = "habitat";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PICTUREURL = "url";


    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_LENGTH + " INTEGER NOT NULL, " + COLUMN_WEIGHT + " INTEGER NOT NULL, " + COLUMN_SHORTDISCRIP +  " TEXT NOT NULL, "
            + COLUMN_POSSIBLESITE + " TEXT NOT NULL, " + COLUMN_POSSIBLEMONTH + " TEXT NOT NULL, " + COLUMN_FEATURE + " TEXT NOT NULL, " + COLUMN_SCIENTIFICNAME + " TEXT NOT NULL, " + COLUMN_HABITAT + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL, " + COLUMN_PICTUREURL+ " TEXT NOT NULL " + ")";

    private long _id;
    private String name;
    private int length;
    private int weight;
    private String shortDiscrip;
    private String possibleSite;
    private String possibleMonth;
    private String feature;
    private String scientificName;
    private String habitat;
    private String description;
    private String url;

    public Whale() {
        _id = 0;
        name = "";
        length = 0;
        weight = 0;
        shortDiscrip= "";
        possibleSite = "";
        possibleMonth = "";
        feature = "";
        scientificName = "";
        habitat = "";
        description = "";
        url = "";
    }

    public Whale(long _id){
        this._id = _id;
    }

    public Whale(long _id, String name, int length, int weight, String shortDiscrip,String possibleSite, String possibleMonth,String feature, String scientificName, String habitat, String description, String url) {
        this._id = _id;
        this.name = name;
        this.length = length;
        this.weight = weight;
        this.shortDiscrip = shortDiscrip;
        this.possibleSite = possibleSite;
        this.possibleMonth = possibleMonth;
        this.feature = feature;
        this.scientificName = scientificName;
        this.habitat = habitat;
        this.description = description;
        this.url = url;
    }

    //Parcelable passing data may not needed not sure yet.
    protected Whale(Parcel in){
        _id = in.readLong();
        name = in.readString();
        length = in.readInt();
        weight = in.readInt();
        shortDiscrip = in.readString();
        possibleSite = in.readString();
        possibleMonth = in.readString();
        feature = in.readString();
        scientificName = in.readString();
        habitat = in.readString();
        description = in.readString();
        url = in.readString();
    }


    public static final Creator<Whale> CREATOR = new Creator<Whale>() {
        @Override
        public Whale createFromParcel(Parcel in) {
            return new Whale(in);
        }

        @Override
        public Whale[] newArray(int size) {
            return new Whale[size];
        }
    };

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getShortDiscrip() {
        return shortDiscrip;
    }

    public void setShortDiscrip(String shortDiscrip) {
        this.shortDiscrip = shortDiscrip;
    }

    public String getPossibleSite() {
        return possibleSite;
    }

    public void setPossibleSite(String possibleSite) {
        this.possibleSite = possibleSite;
    }

    public String getPossibleMonth() {
        return possibleMonth;
    }

    public void setPossibleMonth(String possibleMonth) {
        this.possibleMonth = possibleMonth;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeString(name);
        dest.writeInt(length);
        dest.writeInt(weight);
        dest.writeString(shortDiscrip);
        dest.writeString(possibleSite);
        dest.writeString(possibleMonth);
        dest.writeString(feature);
        dest.writeString(scientificName);
        dest.writeString(habitat);
        dest.writeString(description);
        dest.writeString(url);
    }
}
