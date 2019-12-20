package com.bhavanichandra.bisync.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "bisyncGateway")
public interface IBisyncGateway {

    @Gateway(requestChannel = "bisync.request.channel")
    Object processMessage(Message<?> obj);
}
