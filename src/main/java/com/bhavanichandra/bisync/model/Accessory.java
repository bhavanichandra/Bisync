
package com.bhavanichandra.bisync.model;


public class Accessory {

    private AccessoryTypes type;
    private String imageUrl;
    private String altText;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Accessory() {
    }

    /**
     * 
     * @param altText
     * @param imageUrl
     * @param type
     */
    public Accessory(AccessoryTypes type, String imageUrl, String altText) {
        super();
        this.type = type;
        this.imageUrl = imageUrl;
        this.altText = altText;
    }

    public AccessoryTypes getType() {
        return type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

}
