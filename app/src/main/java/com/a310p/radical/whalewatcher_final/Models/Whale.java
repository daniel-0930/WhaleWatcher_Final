package com.a310p.radical.whalewatcher_final.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Agency model to used as a tour agency object
 * version 1.3
 * @author Zeyao Zhang
 * @since 22/8/2017
 */

public class Whale implements Parcelable{

    //Declare static field of table column in SQLite database
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

    //Declare create statement for creating table in local database
    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_LENGTH + " INTEGER NOT NULL, " + COLUMN_WEIGHT + " INTEGER NOT NULL, " + COLUMN_SHORTDISCRIP +  " TEXT NOT NULL, "
            + COLUMN_POSSIBLESITE + " TEXT NOT NULL, " + COLUMN_POSSIBLEMONTH + " TEXT NOT NULL, " + COLUMN_FEATURE + " TEXT NOT NULL, " + COLUMN_SCIENTIFICNAME + " TEXT NOT NULL, " + COLUMN_HABITAT + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL, " + COLUMN_PICTUREURL+ " TEXT NOT NULL " + ")";

    //Declare field of whale
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


    /**
     * Default constructor with certain default variable
     */
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

    /**
     * Constructor with id
     * @param _id the id of the whale
     */
    public Whale(long _id){
        this._id = _id;
    }

    /**
     * Constructor with full fields
     * @param _id the id of the whale
     * @param name the name of the whale
     * @param length the length of the whale
     * @param weight the weight of the whale
     * @param shortDiscrip the short discription of whale for showing in the list
     * @param possibleSite the possible sites of whale in australia
     * @param possibleMonth the possible month best for whale watching
     * @param feature the feature of the whale
     * @param scientificName the scientific name of the whale
     * @param habitat the habitat of the whale
     * @param description the long description of the whale
     * @param url the image url of the whale
     * */
    public Whale(long _id, String name, int length, int weight, String shortDiscrip,String possibleSite, String possibleMonth,
                 String feature, String scientificName, String habitat, String description, String url) {
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
    /**
     * Whale object that can pass between activities
     * @param in the parcel variable to read
     * */
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

    //Creator for whale in parcelable
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

    /**
     * Get the whale's id
     * @return long the id of the whale
     * */
    public long get_id() {
        return _id;
    }


    /**
     * Set the id of whale
     * @param _id whale's id
     * */
    public void set_id(long _id) {
        this._id = _id;
    }

    /**
     * Get the whale's name
     * @return String the name of the whale
     * */
    public String getName() {
        return name;
    }

    /**
     * Set the name of whale
     * @param name whale's name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the whale's length
     * @return int the length of the whale
     * */
    public int getLength() {
        return length;
    }

    /**
     * Set the length of whale
     * @param length whale's id
     * */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Get the whale's weight
     * @return int the weight of the whale
     * */
    public int getWeight() {
        return weight;
    }

    /**
     * Set the weight of whale
     * @param weight whale's weight
     * */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Get the whale's short discription
     * @return String the short discription of the whale
     * */
    public String getShortDiscrip() {
        return shortDiscrip;
    }

    /**
     * Set the short discription of whale
     * @param shortDiscrip whale's short discription
     * */
    public void setShortDiscrip(String shortDiscrip) {
        this.shortDiscrip = shortDiscrip;
    }

    /**
     * Get the whale's possible site
     * @return String the possible site of the whale
     * */
    public String getPossibleSite() {
        return possibleSite;
    }

    /**
     * Set the possible site of whale
     * @param possibleSite whale's possible site
     * */
    public void setPossibleSite(String possibleSite) {
        this.possibleSite = possibleSite;
    }

    /**
     * Get the whale watching possible month
     * @return String the possible month of the whale watching
     * */
    public String getPossibleMonth() {
        return possibleMonth;
    }

    /**
     * Set the possible month of whale watching
     * @param possibleMonth whale's possible month shown in Australia
     * */
    public void setPossibleMonth(String possibleMonth) {
        this.possibleMonth = possibleMonth;
    }

    /**
     * Get the whale's feature
     * @return String the feature of the whale
     * */
    public String getFeature() {
        return feature;
    }

    /**
     * Set the feature of whale
     * @param feature whale's feature
     * */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * Get the whale's scientific name
     * @return String the scientific name of the whale
     * */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Set the scientific name of whale
     * @param scientificName whale's scientific name
     * */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * Get the whale's habitat
     * @return habitat the habitat of the whale
     * */
    public String getHabitat() {
        return habitat;
    }

    /**
     * Set the habitat of whale
     * @param habitat whale's feature
     * */
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    /**
     * Get the whale's description
     * @return String the description of the whale
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Set the feature of whale
     * @param description whale's description
     * */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the whale's image url
     * @return String the image url of the whale
     * */
    public String getUrl() {
        return url;
    }

    /**
     * Set the image url of whale
     * @param url whale's image url
     * */
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
