package com.bhavanichandra.bisync.config;

import com.bhavanichandra.bisync.service.IBisync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

import static org.springframework.integration.dsl.IntegrationFlows.from;
import static org.springframework.integration.dsl.MessageChannels.direct;

@Configuration
@ComponentScan(basePackages = "com.bhavanichandra.bisync.*")
@EnableIntegration
@EnableAutoConfiguration
@IntegrationComponentScan(basePackages = "com.bhavanichandra.bisync.*")
public class BisyncConfig {

    private IBisync biSyncService;

    @Autowired
    public BisyncConfig(IBisync biSyncService) {
        this.biSyncService = biSyncService;
    }

    @Bean("bisync.request.channel")
    public MessageChannel inputMessageChannel() {
        return direct().get();
    }

    @Bean
    public IntegrationFlow processFlow() {
        return from("bisync.request.channel").handle(biSyncService, "constructSlackMessage").get();
    }
}
