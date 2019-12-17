package com.bhavanichandra.bisync.service;

import com.bhavanichandra.bisync.model.Contact;
import com.bhavanichandra.bisync.model.SlackModalMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import static org.springframework.messaging.support.MessageBuilder.withPayload;
@Service
public class BisyncService implements IBisync {


    private ObjectMapper objectMapper;

    @Autowired
    public BisyncService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Message<SlackModalMessage> constructSlackMessage(Message<Contact> contactMessage) throws Exception {
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contactMessage));


        return withPayload(new SlackModalMessage()).build();
    }
}
