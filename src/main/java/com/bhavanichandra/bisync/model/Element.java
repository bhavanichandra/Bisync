
package com.bhavanichandra.bisync.model;


public class Element {

    private String type;
    private CommonModel text;
    private String value;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Element() {
    }

    /**
     * 
     * @param text
     * @param type
     * @param value
     */
    public Element(String type, CommonModel text, String value) {
        super();
        this.type = type;
        this.text = text;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CommonModel getText() {
        return text;
    }

    public void setText(CommonModel text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
