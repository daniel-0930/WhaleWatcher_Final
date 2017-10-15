package com.a310p.radical.whalewatcher_final.Models;

/**
 * Agency model to used as a tour agency object
 * version 1.0
 * @author Yinhong Ren
 * @since 19/9/2017
 */



public class Agency {


    //Declare variables
    private String agency_website_url;
    private String agency_name;
    private String agency_location;
    private String agency_image_url;


    /**
    * Default constructor
    */
    public Agency() {
    }

    /**
    * Constructor contains parameters
    * @param agency_website_url website url of the agency
    * @param agency_name name of the agency
    * @param agency_location the address of the agency
    * @param agency_image_url the image logo url of the agency
    */
    public Agency(String agency_website_url, String agency_name, String agency_location, String agency_image_url) {
        this.agency_website_url = agency_website_url;
        this.agency_name = agency_name;
        this.agency_location = agency_location;
        this.agency_image_url = agency_image_url;
    }

    /**
    * Get website url of the Agency
    * @return agency_website_url agency's website url
    */
    public String getAgency_website_url() {
        return agency_website_url;
    }

    /**
    * Set website url of the Agency
    * @param agency_website_url agency's website url
    */
    public void setAgency_website_url(String agency_website_url) {
        this.agency_website_url = agency_website_url;
    }
    /**
    * Get name of the Agency
    * @return agency_name agency's name
    */
    public String getAgency_name() {
        return agency_name;
    }

    /**
    * Set name of the Agency
    * @param agency_name agency's name
    */
    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }


    /**
    * Get location address of the Agency
    * @return agency_location agency's address of location
    */
    public String getAgency_location() {
        return agency_location;
    }


    /**
    * Set location address of the Agency
    * @param agency_location agency's address of location
    */
    public void setAgency_location(String agency_location) {
        this.agency_location = agency_location;
    }

    /**
    * Get image url of the Agency
    * @return agency_image_url agency's image url
    */
    public String getAgency_image_url() {
        return agency_image_url;
    }

    /**
    * Set image url of the Agency
    * @param agency_image_url agency's image url
    */
    public void setAgency_image_url(String agency_image_url) {
        this.agency_image_url = agency_image_url;
    }
}
