package com.bhavanichandra.bisync.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Notification {

    @JacksonXmlProperty(localName = "Id")
    private String id;

    @JacksonXmlProperty(localName = "sObject")
    private SObject sObject;

    public SObject getsObject() {
        return sObject;
    }

    public void setsObject(SObject sObject) {
        this.sObject = sObject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
