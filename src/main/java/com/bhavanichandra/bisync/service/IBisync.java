package com.bhavanichandra.bisync.service;

import com.bhavanichandra.bisync.domain.Envelope;
import com.bhavanichandra.bisync.model.SlackMessage;
import org.springframework.messaging.Message;

public interface IBisync {
    Message<SlackMessage> constructSlackMessage(Message<Envelope> contactMessage) throws Exception;
}
