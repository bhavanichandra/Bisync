package com.bhavanichandra.bisync.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Notifications {

    @JacksonXmlProperty(localName = "OrganizationId")
    private String OrganizationId;
    @JacksonXmlProperty(localName = "ActionId")
    private String ActionId;
    @JacksonXmlProperty(localName = "SessionId")
    private String SessionId;
    @JacksonXmlProperty(localName = "EnterpriseUrl")
    private String EnterpriseUrl;
    @JacksonXmlProperty(localName = "PartnerUrl")
    private String PartnerUrl;
    @JacksonXmlProperty(localName = "Notification")
    private List<Notification> notification;

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    public String getActionId() {
        return ActionId;
    }

    public void setActionId(String actionId) {
        ActionId = actionId;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public String getEnterpriseUrl() {
        return EnterpriseUrl;
    }

    public void setEnterpriseUrl(String enterpriseUrl) {
        EnterpriseUrl = enterpriseUrl;
    }

    public String getPartnerUrl() {
        return PartnerUrl;
    }

    public void setPartnerUrl(String partnerUrl) {
        PartnerUrl = partnerUrl;
    }

    public List<Notification> getNotification() {
        return notification;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }
}
