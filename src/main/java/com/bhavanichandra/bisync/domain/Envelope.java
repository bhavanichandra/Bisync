package com.bhavanichandra.bisync.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAccessorType;

import static javax.xml.bind.annotation.XmlAccessType.PROPERTY;

@JacksonXmlRootElement(localName = "Envelope")
@XmlAccessorType(PROPERTY)
public class Envelope {

    @JacksonXmlProperty(localName = "Body")
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
