package com.bhavanichandra.bisync.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IBisyncGateway {

    @Gateway(requestChannel = "bisync.request.channel")
    Object processMessage(Object obj);
}
