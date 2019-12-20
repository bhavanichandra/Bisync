package com.bhavanichandra.bisync.controller;

import com.bhavanichandra.bisync.domain.Envelope;
import com.bhavanichandra.bisync.gateway.IBisyncGateway;
import com.bhavanichandra.bisync.model.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.http.MediaType.*;
import static org.springframework.messaging.support.MessageBuilder.withPayload;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/api")
public class BisyncController {


    private IBisyncGateway gateway;

    @Autowired
    public BisyncController(IBisyncGateway gateway) {
        this.gateway = gateway;
    }

    @RequestMapping(path = "/trigger", consumes = {APPLICATION_XML_VALUE, APPLICATION_JSON_VALUE}, produces = {APPLICATION_XML_VALUE, APPLICATION_JSON_VALUE}, method = POST)
    public Object triggerFromSalesforce(@RequestBody Envelope requestFromSFDC) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(requestFromSFDC));

        Message<Envelope> envelopeMessage = withPayload(requestFromSFDC).build();
        return gateway.processMessage(envelopeMessage);
    }

    @RequestMapping(path = "/slashcommand", method = POST, consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public Object triggerFromSlack(HashMap<String, String> slackRequest) throws Exception {
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(slackRequest));
        return slackRequest;
    }
}
