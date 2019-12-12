package com.bhavanichandra.bisync.controller;

import com.bhavanichandra.bisync.gateway.IBisyncGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/api")
public class BisyncController {


    private IBisyncGateway gateway;

    @Autowired
    public BisyncController(IBisyncGateway gateway) {
        this.gateway = gateway;
    }

    @RequestMapping(path = "/trigger", consumes = APPLICATION_XML_VALUE, produces = APPLICATION_JSON_VALUE, method = POST)
    public Object triggerFromSalesforce(@RequestBody HashMap<String, Object> requestFromSFDC) {
        return gateway.processMessage(requestFromSFDC);
    }
}
