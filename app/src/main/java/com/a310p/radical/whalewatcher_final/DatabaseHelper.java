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
        name = "Blue Whale";
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
        name = "Humpback Whale";
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

        //whale 5
        name = "Sperm Whale";
        length = 11;
        weight = 15000;
        shortDiscrip = "Largest toothed whale with larger male and regualr female whale shape.";
        possiblesite = "Sperm Whales have concentrated in a narrow area only a few miles wide at shelf edge off Albany, Western Australia, "+
                "moving westwards through the year. Off the Western Australian coast, where the continental shelf slopes less steeply, "
                +"Sperm Whales appear to be less concentrated close to shelf edge and more widely dispersed offshore.Similar concentrations "
                +"of Sperm Whales have been found elsewhere in Australia, such as south-west of Kangaroo Island, South Australia.";
        possibleMonth = "Feburary";
        feature = "dark grey, bright white mouth, white patches on the belly";
        scientificName = "Physeter macrocephalus";
        habitat = "Sperm Whales tend to inhabit offshore areas with a water depth of 600 m or more, and are uncommon in waters less than 300 m deep."
                +" Female Sperm Whales are generally found in deep waters of low latitudes. These conditions generally correspond to sea "
                +"surface temperatures greater than 15 °C, and while female Sperm Whales are sometimes seen near oceanic islands, they are "
                +"typically far from land. Concentrations of Sperm Whales are found where the seabed rises steeply from great depth, "
                +"and are probably associated with concentrations of major food in areas of upwelling. Immature males will stay with females "
                +"in tropical and subtropical waters until they begin to slowly migrate towards the poles, anywhere between ages 4 and 21 "
                +"years old. Older, larger males are generally found south of about 45° S down to the edge of the Antarctic pack ice . On "
                +"occasion, however, these males will return to the warm water breeding area.";
        description = "Sperm Whales (Physeter macrocephalus) are the largest of the odontocetes (toothed whales) and the "+
                "most sexually dimorphic cetaceans, with males considerably larger than females. Adult Sperm Whale females may grow to "
                +"lengths of 11 m and weigh 15 tonnes, while adult males reach about 16 m and may weigh as much as 45 tonnes."
                +"The Sperm Whale is distinguished by its extremely large head, which takes up 25–35% of its total body length . " +
                "The lower jaw is narrow and underslung . It is the only living cetacean that has a single blowhole asymmetrically " +
                "situated on the left side of the head near the tip. Sperm Whales have the largest brain of any animal, however, compared to " +
                "their large body size, the brain is not exceptional in size. "
                +"Sperm Whales, like other toothed whales, are gregarious and live in groups of up to 50 individuals, although male Sperm Whales are sometimes solitary in high latitudes";
        pictureurl = "http://www.fisheries.noaa.gov/pr/images/cetaceans/spermwhale_timcole_nmfs.jpg";
        addWhale(new Whale(5, name,length, weight, shortDiscrip, possiblesite, possibleMonth, feature, scientificName, habitat, description, pictureurl));

        //whale 6
        name = "Sei Whale";
        length = 17;
        weight = 45000;
        shortDiscrip = "The third-largest rorqual after the blue whale and the fin whale";
        possiblesite = "Sei whales have been infrequently recorded in Australian waters. " +
                "Sei whales were thought to be the most common whales reported by whalers off Albany, Western Australia. "
                + "Sei whales have been sighted 20–60 km offshore on the continental shelf in the Bonney Upwelling";
        possibleMonth = "November,December,Janurary,Feburary,March,April,May,June";
        feature = "dark grey or blue-grey, light coloured circular scars";
        scientificName = "Balaenoptera borealis";
        habitat = "The Australian Antarctic waters are important feeding grounds for sei whales, as are temperate, cool waters." +
                " Sightings of sei whales feeding in the Bonney Upwelling area indicate that this area is potentially also an important " +
                "feeding ground. Breeding occurs in tropical and subtropical waters.";
        description = "Sei whales are dark grey or blue-grey on their back and sides. " +
                "The undersides and sides may appear mottled with light coloured circular scars caused by various types of parasites, " +
                "including scars from the bite of the 'cookie-cutter' shark (Isistius brasiliensis).\n" +
                "At sexual maturity, sei whales are approximately 12–16 m long, although they can reach lengths of 17.7 m in males and 21 m in females" +
                " . Adult females are about 0.5–0.6 m longer than males, and sei whales of the Southern Hemisphere are larger than those of the " +
                "Northern Hemisphere. The body of the sei whale is slim, streamlined and laterally compressed in the caudal region.\n";
        pictureurl = "http://www.antarctica.gov.au/__data/assets/image/0007/148795/varieties/antarctic.jpg";
        addWhale(new Whale(6, name,length, weight, shortDiscrip, possiblesite, possibleMonth, feature, scientificName, habitat, description, pictureurl));

        //whale 7
        name = "Fin Whale";
        length = 20;
        weight = 70000;
        shortDiscrip = "The second largest whale species";
        possiblesite = "Fin whales have been observed during aerial surveys in South Australian waters between November and May, however, fin whale distribution in Australian waters is known primarily from stranding events and whaling records."
                +"Fin whales have been sighted inshore in the proximity of the Bonney Upwelling, Victoria, in the summer and autumn months during aerial surveys ";
        possibleMonth = "November,December,Janurary,Feburary,March,April,May,June";
        feature = "dark grey, brownish black dorsally, grading to pale, or white along the abdomen";
        scientificName = "Balaenoptera physalis";
        habitat = "Fin whales are widely distributed in both hemispheres between latitudes 20–75° S This species is common in temperate waters, the Arctic Ocean and Southern Ocean. In the Southern Ocean/Subantarctic this species is often found in areas of complex and steep bathymetry, such as deep ravines, where fish and other prey are known to concentrate";
        description = "The fin whale is the second-largest whale species, after the blue whale (Balaenoptera musculus). Adult whales range between 20 and 27 m long and weigh more than 70 tonnes. As with other baleen whales, female fin whales grow to a larger size than males. The fin whale is very streamlined in appearance, with a distinct ridge along the back behind the dorsal fin. The dorsal fin is set two-thirds of the way along the back, and is up to 60 cm tall, curved and often slopes backwards .\n" +
                "Fin whales are more gregarious than other baleen whales, and often occur in groups of 6–10, though single animals and pairs are more common. Aggregations of over 100 whales may be observed on feeding grounds \n";
        pictureurl = "http://www.antarctica.gov.au/__data/assets/image/0008/187433/varieties/antarctic.jpg";
        addWhale(new Whale(7, name,length, weight, shortDiscrip, possiblesite, possibleMonth, feature, scientificName, habitat, description, pictureurl));

        //whale 8
        name = "Minke Whale";
        length = 9;
        weight = 10000;
        shortDiscrip = "Known";
        possiblesite = "Antarctic Minke Whales have been recorded from all States but not in the Northern Territory. Antarctic Minke Whales are currently considered to occur in one location, although taxonomic confusion within the Minke Whales and possible future taxonomic revision of this genus may lead to changes in understanding the stock structure of the Antarctic Minke Whale.";
        possibleMonth = "June,July,August,December,Janurary,Feburary";
        feature = "dark bluish-grey back,  pale grey to white flanks and belly, light grey colour on their flippers, tail flukes is white";
        scientificName = "Balaenoptera bonaerensis";
        habitat = "Antarctic Minke Whales appear to occupy primarily offshore and pelagic habitats within cold temperate to Antarctic waters between 21° S and 65° S."
                +"On the winter breeding grounds, Antarctic Minke Whales appear to occupy pelagic waters exceeding 600 m depth. During the summer, they head for higher latitudes to feed";
        description = "The Antarctic Minke Whale has a dark bluish-grey back, " +
                "sharply contrasting the pale grey to white flanks and belly. The lateral colouration is complex, including a " +
                "crescent-shaped grey streak that extends up each side of the animal, above the flipper insertion and towards " +
                "the dorsal midline where they meet. A pair of grey streaks extend posteriorly (towards the back) for about 0.6 m from " +
                "the blowhole. The flippers are slim and pointed, with no white blaze on the upper surface. Some individuals have a " +
                "two-tone light grey colour on their flippers. Both flipper colour patterns can be present in an individual. " +
                "The underside of the flippers and the tail flukes is white.";
        pictureurl = "http://www.antarctica.gov.au/__data/assets/image/0003/28902/varieties/antarctic.jpg ";
        addWhale(new Whale(8, name,length, weight, shortDiscrip, possiblesite, possibleMonth, feature, scientificName, habitat, description, pictureurl));

        //whale 9
        name = "Killer Whale";
        length = 8;
        weight = 4000;
        shortDiscrip = "The largest member of dophin family";
        possiblesite = "In Australia, Killer Whales are recorded from all states, with concentrations reported " +
                "around Tasmania. Sightings are also frequent in South Australia and Victoria. " +
                " Killer Whales are frequently seen in the Antarctic south of 60° S and have been recorded from Heard and Macquarie Islands." +
                " Macquarie Island appears to be a key locality, with Killer Whales regularly reported there. ";
        possibleMonth = "December,Janurary,Feburary";
        feature = "black, white and grey";
        scientificName = "Orcinus orca";
        habitat = "The preferred habitat of Killer Whales includes oceanic, pelagic and neritic regions, in both warm and cold waters. " +
                "They may be more common in cold, deep waters, but off Australia, Killer Whales are most often seen along the continental " +
                "slope and on the shelf, particularly near seal colonies. Killer Whales have regularly been observed within the Australian" +
                " territorial waters along the ice edge in summer.";
        description = "Killer Whales are the largest member of the dolphin family and are recognisable by their distinctive black, " +
                "white and grey coloration. The head is rounded, with no distinct beak. A white eye patch, or spot, is located just " +
                "above and behind the eye. Just behind the dorsal fin is a grey saddle patch. The Killer Whale's belly, lower " +
                "jaw and the underside of the tail flukes are white. The rest of the body is black. The wide, tall dorsal " +
                "fin is curved backwards in females and is more upright and triangular-shaped in males. The pectoral flippers are " +
                "paddle-shaped. In addition to sexual size dimorphism, male Killer Whale appendages are disproportionately larger than " +
                "in females. Adult male and female Killer Whales attain weights exceeding 4000 kg and 3100 kg, and lengths of 9.8 m " +
                "and 8.5–9.2 m, respectively.";
        pictureurl = "https://www.afsc.noaa.gov/nmml/gallery/cetaceans/images/nmmlweb-killerwhale-8.jpg ";
        addWhale(new Whale(9, name,length, weight, shortDiscrip, possiblesite, possibleMonth, feature, scientificName, habitat, description, pictureurl));
    }
}

