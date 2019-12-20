package com.bhavanichandra.bisync.service;

import com.bhavanichandra.bisync.domain.*;
import com.bhavanichandra.bisync.model.Block;
import com.bhavanichandra.bisync.model.CommonModel;
import com.bhavanichandra.bisync.model.Field;
import com.bhavanichandra.bisync.model.SlackMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bhavanichandra.bisync.model.BlockTypes.SECTION;
import static com.bhavanichandra.bisync.util.TypeConstants.MAIN_NOTIFICATION_MESSAGE;
import static com.bhavanichandra.bisync.util.TypeConstants.MARKDOWN;
import static com.bhavanichandra.bisync.util.Util.getLowerCaseValue;
import static com.bhavanichandra.bisync.util.Util.modelToMap;
import static org.springframework.messaging.support.MessageBuilder.withPayload;
@Service
public class BisyncService implements IBisync {


    private ObjectMapper objectMapper;

    @Autowired
    public BisyncService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Message<SlackMessage> constructSlackMessage(Message<Envelope> envelopeMessage) throws Exception {
        Envelope envelope = envelopeMessage.getPayload();
        Body body = envelope.getBody();
        Notifications notifications = body.getNotifications();
        SlackMessage slackMessage = new SlackMessage();
        List<Notification> notificationList = notifications.getNotification();
        List<Block> blocks = new ArrayList<>();
        Block mainBlock = new Block();
        mainBlock.setType(getLowerCaseValue(SECTION));
        CommonModel text = new CommonModel();
        text.setType(MARKDOWN);
        text.setEmoji(true);
        text.setText(MAIN_NOTIFICATION_MESSAGE);
        mainBlock.setText(text);
        blocks.add(mainBlock);
        Block fieldBlock = new Block();
        fieldBlock.setType(getLowerCaseValue(SECTION));
        List<Field> fields = new ArrayList<>();
        for(Notification notification : notificationList) {
            SObject sObject = notification.getsObject();
            fields = modelToMap(sObject);
        }
        fieldBlock.setFields(fields);
        blocks.add(fieldBlock);
        slackMessage.setBlocks(blocks);
        return withPayload(slackMessage).build();
    }
}
