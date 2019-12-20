package com.bhavanichandra.bisync.service;

import com.bhavanichandra.bisync.domain.Envelope;
import com.bhavanichandra.bisync.model.Contact;
import com.bhavanichandra.bisync.model.SlackModalMessage;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

public interface IBisync {
    Message<SlackModalMessage> constructSlackMessage(Message<Envelope> contactMessage) throws Exception;
}
