package com.bhavanichandra.bisync.service;

import com.bhavanichandra.bisync.domain.*;
import com.bhavanichandra.bisync.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.bhavanichandra.bisync.model.BlockTypes.SECTION;
import static com.bhavanichandra.bisync.util.TypeConstants.MAIN_NOTIFICATION_MESSAGE;
import static com.bhavanichandra.bisync.util.TypeConstants.MARKDOWN;
import static com.bhavanichandra.bisync.util.Util.getLowerCaseValue;
import static com.bhavanichandra.bisync.util.Util.modelToMap;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

@Service
public class BisyncService implements IBisync {


    private static final Logger log = LogManager.getLogger();


    private ObjectMapper objectMapper;
    @Value("${slack.token}")
    private String token;
    @Value("${slack.channel}")
    private String channel;

    @Autowired
    public BisyncService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Message<SlackMessage> constructSlackMessage(Message<Envelope> envelopeMessage) throws Exception {
        log.info("Request to service: " + objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(envelopeMessage));
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
        text.setText(MAIN_NOTIFICATION_MESSAGE);
        mainBlock.setText(text);
        blocks.add(mainBlock);
        Block fieldBlock = new Block();
        fieldBlock.setType(getLowerCaseValue(SECTION));
        List<Field> fields = new ArrayList<>();
        for (Notification notification : notificationList) {
            SObject sObject = notification.getsObject();
            fields = modelToMap(sObject);
        }

        fieldBlock.setFields(fields.stream().filter(f -> f.getType() != null && f.getText() != null).collect(Collectors.toList()));
        blocks.add(fieldBlock);
        slackMessage.setChannel(channel);
        slackMessage.setToken(token);
        slackMessage.setText("");
        slackMessage.setBlocks(blocks);
        log.info("Slack Message: " + objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(slackMessage));
        return withPayload(slackMessage).build();
    }

    @Override
    public Message<?> slackResponse(Message<?> slackResponse) throws Exception {
        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(slackResponse));
        return withPayload(new SlackModalMessage()).build();
    }
}
