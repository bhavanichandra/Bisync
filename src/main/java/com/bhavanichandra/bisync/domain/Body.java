package com.bhavanichandra.bisync.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Body {

    @JacksonXmlProperty(localName = "notifications")
   private Notifications notifications;

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }
}
