package com.bhavanichandra.bisync.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(value = NON_EMPTY)
public class Block {

    private String type;
    private CommonModel text;
    private Accessory accessory;
    private List<Element> elements = new ArrayList<>();
    private List<Field> fields = new ArrayList<>();


    /**
     * No args constructor for use in serialization
     */
    public Block() {
    }

    public Block(String type, CommonModel text, Accessory accessory, List<Element> elements, List<Field> fields) {
        this.type = type;
        this.text = text;
        this.accessory = accessory;
        this.elements = elements;
        this.fields = fields;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
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
