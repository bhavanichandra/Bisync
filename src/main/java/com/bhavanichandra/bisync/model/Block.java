package com.bhavanichandra.bisync.model;

import java.util.ArrayList;
import java.util.List;

public class Block {

    private String type;
    private CommonModel text;
    private Accessory accessory;
    private List<Element> elements = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     */
    public Block() {
    }

    /**
     * @param elements
     * @param text
     * @param type
     * @param accessory
     */
    public Block(String type, CommonModel text, Accessory accessory, List<Element> elements) {
        super();
        this.type = type;
        this.text = text;
        this.accessory = accessory;
        this.elements = elements;
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

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}
