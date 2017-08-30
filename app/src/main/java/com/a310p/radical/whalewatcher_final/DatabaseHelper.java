package com.a310p.radical.whalewatcher_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.a310p.radical.whalewatcher_final.Models.Whale;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by zhangzeyao on 23/8/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "WhaleDB";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Whale.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Whale.TABLE_NAME);
        onCreate(db);
    }


    public void addWhale (Whale whale){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Whale.COLUMN_NAME,whale.getName());
        values.put(Whale.COLUMN_LENGTH,whale.getLength());
        values.put(Whale.COLUMN_WEIGHT,whale.getWeight());
        values.put(Whale.COLUMN_SHORTDISCRIP,whale.getShortDiscrip());
        values.put(Whale.COLUMN_POSSIBLESITE,whale.getPossibleSite());
        values.put(whale.COLUMN_POSSIBLEMONTH,whale.getPossibleMonth());
        values.put(Whale.COLUMN_FEATURE, whale.getFeature());
        values.put(Whale.COLUMN_SCIENTIFICNAME, whale.getScientificName());
        values.put(Whale.COLUMN_HABITAT,whale.getHabitat());
        values.put(Whale.COLUMN_DESCRIPTION, whale.getDescription());
        values.put(Whale.COLUMN_PICTUREURL,whale.getUrl());
        db.insert(Whale.TABLE_NAME,null,values);
        db.close();
    }

    public HashMap<Long, Whale> getAllWhale (){
        HashMap<Long, Whale> courses = new LinkedHashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Whale.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                Whale whale = new Whale(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                        cursor.getString(7), cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11));
                courses.put(whale.get_id(),whale);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return courses;
    }

    public void createWhaleDatabase(){
        String name = "";
        int length = 0;
        int weight = 0;
        String shortDiscrip = "";
        String possiblesite = "";
        String possibleMonth = "";
        String feature = "";
        String scientificName = "";
        String habitat = "";
        String description = "";
        String pictureurl = "";

        //whale 1
        name = "Blue whales";
        length = 33 ;
        weight = 180000 ;
        shortDiscrip="The largest animals ever known that lived on the earth";
        possiblesite = "Off the coasts of Victoria and South Australia in an area known as the Bonney upwelling and off the Western Australian coast in the Perth Canyon";
        possibleMonth = "December,Janurary,Feburary";
        feature = "Blue whales have grey blue skin with white spots and a small dorsal fin set far back on their body. Blue Whales are silver-grey to dark grey in colour, with light grey spots across the skin which appears bright blue underwater. ";
        scientificName = "Balaenoptera musculus";
        habitat = "Blue Whales are found throughout the world’s oceans.  In the summer months they tend to reside in sub-Antarctic and Antarctic waters. In the winter months, they move to breeding grounds in warmer tropical waters.";
        description = "Blue whales are typically seen in deeper offshore waters where large upwelling bring krill, their preferred food, to the surface.\n"+" Blue Whales don’t have teeth; instead they eat by sifting seawater through their mouth to catch krill. The seawater is filtered through hundreds of giant sieves in their mouth called baleen plates. Blue Whales can carry lice that live and feed on loose skin. Blue Whales can live for up to 90 years. ";
        pictureurl = "https://www.afsc.noaa.gov/nmml/gallery/cetaceans/images/nmmlweb-bluewhale-6.jpg";
        addWhale(new Whale(1, name,length, weight,shortDiscrip, possiblesite,possibleMonth, feature, scientificName, habitat, description, pictureurl));

        //whale 2
        name = "Humpback Whales";
        length = 16 ;
        weight = 40000 ;
        shortDiscrip = "The most common whale you will see when whale watching";
        possiblesite = "East and west coast of Australia on their annual migration to and from Antarctic waters however they will sometimes be seen off South Australia";
        possibleMonth = "April,May,June,July,Sepetember,October.";
        feature = "Grey to black on the bac, varying patterns of white on the underside on their body, tail fluke and flippers";
        scientificName = "Megaptera novaeangliae";
        habitat = "Humpback Whales are found in all the oceans of the world from polar to tropical waters. They are regularly found in Arctic, Atlantic and Pacific oceans and at times in Antarctica waters. \n" +
                "There are three separate populations of humpbacks: \n" +
                "·those living in the North Pacific Ocean, \n" +
                "·those in the North Atlantic Ocean, \n" +
                "and those roving the oceans of the Southern Hemisphere";
        description = "Humpbacks are very acrobatic, often leaping or ‘breaching’ high out of the water before crashing back down. Sometimes they twirl around in the air while breaching. Nobody’s sure why they do this, but it could be for communication, play or to loosen skin parasites.\n" +
                "Humpback whales belong to the genus Megaptera, which is derived from the Greek words ‘mega’ meaning great and ‘pteron’ meaning wing. The ‘great-wing’ the Greeks were referring to is the pectoral (side) fins which can be up to 6m long on a Humpback. That’s almost the length of two cars!\n" +
                "The Humpback Whale has one of the longest migrations of any mammal in the world, travelling over 12,000km annually.";
        pictureurl = "https://www.afsc.noaa.gov/nmml/gallery/cetaceans/images/nmmlweb-humpbackwhale-10.jpg";
        addWhale(new Whale(2, name,length, weight,shortDiscrip, possiblesite, possibleMonth, feature, scientificName, habitat, description, pictureurl));
        //whale 3
        name = "Bottlenose Dolphin";
        length = 3;
        weight = 400;
        shortDiscrip="The most known dolphin as the intelligent and charismatic stars";
        possiblesite = "In Victoria, populations of Bottlenose Dolphins can be seen along the whole of the coastline, including within Port Phillip Bay, Western Port and Gippsland Lakes. It is thought that the Port Phillip Bay population is genetically different from the other offshore dolphin population";
        possibleMonth = "December,Janurary,Feburary";
        feature = "The bottlenose dophin's coloration varies from shades of gray (gray-green, gray-brown, light gray) on the backs to white below the jaw and on the belly.";
        scientificName = "Tursiops truncatus";
        habitat = "Bottlenose Dolphins are found throughout tropical and temperate oceans.\n" +
                "Along the Victorian coast Bottlenose Dolphins enter all the bays, estuaries and coastal lakes and occasionally enter the tidal reaches of large and small rivers eg. Bass, Yarra and Maribyrnong. ";
        description = "Bottlenose Dolphins are actually small whales, and belong to the group known as 'toothed whales'. \n" +
                "They are air breathing mammals so, even though they have adapted to the marine environment, they still must come to the surface to breathe through the blowhole on top of their heads. \n"+
                "The Bottlenose Dolphin is commonly seen in groups or pods, containing anything from two or three individuals to more than a thousand.\n" +
                "The Bottlenose Dolphin has a short rounded snout, described as bottle-shaped. Their lower jaw is typically white tipped. \n" +
                "The large dorsal fin is slightly hooked and set half way along the body (see diagram). \n" +
                "Overall, the body of a Bottlenose Dolphin is dark bluish to grey tones with an indistinct paler grey wash on the flanks fading into an off-white belly.\n";
        pictureurl = "https://www.afsc.noaa.gov/nmml/education/images/bottlenose.jpg";
        addWhale(new Whale(3, name,length, weight,shortDiscrip, possiblesite,possibleMonth, feature, scientificName, habitat, description, pictureurl));
        //whale 4
        name = "Southern Right Whale";
        length = 18;
        weight = 80000;
        shortDiscrip = "The only large whale spent long periods of time close to shore.";
        possiblesite = "During winter Southern Right Whales can be found along most of Victoria’s coastline, although they are more commonly seen in south western Victoria.\n"+
                "Logan's Beach at Warrnambool has been a favoured area for Southern Rights in Victoria for many years. Nobody’s quite sure why, but Logan’s Beach is the only place where females return regularly from Antarctic waters to nurse and feed their young every year";
        possibleMonth = "June,July,August";
        feature = "Black body, many have irregular white blotches underneath. Callosities form distinctive white markings on head. ";
        scientificName = "Eubalaena australis";
        habitat = "Southern Right Whales live in cool seas in the Southern Hemisphere. They migrate each year meaning they travel between different areas at different times. \n" +
                "During summer they feed in sub-Antarctic waters of the Southern Ocean. \n" +
                "During winter and spring, they move to warmer waters off the southern coast of Australia, South America, South Africa and New Zealand.";
        description = "The majestic Southern Right Whale is one of Victoria’s most loved species and is the only large whale to spend long periods of time close to shore. From Nelson to Mallacoota, there’s a good chance you’ll see one of these special creatures during winter.\n"
                +"Southern Right Whales can be identified by the lack of a dorsal fin and distinctive skin growths called callosities on their heads. These look like barnacles and occur on top of the head, chin and lower jaw. \n" +
                "Callosities are as unique as a human’s fingerprint. This is how individual whales can be identified.\n" +
                "Southern Right Whales have two blow holes, which result in a bushy “V” shaped blow that can reach up to 5m high. \n";
        pictureurl = "https://www2.delwp.vic.gov.au/__data/assets/image/0028/74359/photoflight-13-aug-2009.JPG";
        addWhale(new Whale(4, name,length, weight, shortDiscrip, possiblesite, possibleMonth, feature, scientificName, habitat, description, pictureurl));
//        //whale 5
//        name = "";
//        length = ;
//        weight = ;
//        possiblesite = "";
//        feature = "";
//        scientificName = "";
//        habitat = "";
//        description = "";
//        pictureurl = "";
//        addWhale(new Whale(1, name,length, weight, possiblesite, feature, scientificName, habitat, description, pictureurl));
//        //whale 6
//        name = "";
//        length = ;
//        weight = ;
//        possiblesite = "";
//        feature = "";
//        scientificName = "";
//        habitat = "";
//        description = "";
//        pictureurl = "";
//        addWhale(new Whale(1, name,length, weight, possiblesite, feature, scientificName, habitat, description, pictureurl));

//      addWhale(new Whale());
    }
}

