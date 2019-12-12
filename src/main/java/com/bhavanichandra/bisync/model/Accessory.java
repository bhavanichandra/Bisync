
package com.bhavanichandra.bisync.model;


public class Accessory {

    private String type;
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
    public Accessory(String type, String imageUrl, String altText) {
        super();
        this.type = type;
        this.imageUrl = imageUrl;
        this.altText = altText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
