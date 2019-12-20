package com.bhavanichandra.bisync.service;

import com.bhavanichandra.bisync.domain.*;
import com.bhavanichandra.bisync.model.Block;
import com.bhavanichandra.bisync.model.Contact;
import com.bhavanichandra.bisync.model.SlackModalMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.messaging.support.MessageBuilder.withPayload;
@Service
public class BisyncService implements IBisync {


    private ObjectMapper objectMapper;

    @Autowired
    public BisyncService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Message<SlackModalMessage> constructSlackMessage(Message<Envelope> envelopeMessage) throws Exception {
        Envelope envelope = envelopeMessage.getPayload();
        Body body = envelope.getBody();
        Notifications notifications = body.getNotifications();
        List<Notification> notificationList = notifications.getNotification();
        List<Block> blocks = new ArrayList<>();
        for(Notification notification : notificationList) {
            Block block = new Block();
            SObject sObject = notification.getsObject();

        }

        return withPayload(new SlackModalMessage()).build();
    }
}
