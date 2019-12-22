package com.bhavanichandra.bisync.config;

import com.bhavanichandra.bisync.service.IBisync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.MessageChannel;

import java.net.URI;
import java.net.URISyntaxException;

import static com.bhavanichandra.bisync.util.Util.httpHeadersMapper;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.integration.dsl.IntegrationFlows.from;
import static org.springframework.integration.dsl.MessageChannels.direct;

@Configuration
@ComponentScan(basePackages = "com.bhavanichandra.bisync.*")
@EnableIntegration
@IntegrationComponentScan(basePackages = "com.bhavanichandra.bisync.*")
public class BisyncConfig {

    @Value("${slack.url}")
    private String url;

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
    public IntegrationFlow processFlow() throws URISyntaxException {
        System.out.println("URL: " + url);
        return from("bisync.request.channel")
                .handle(biSyncService, "constructSlackMessage")
                .handle(Http.outboundGateway(new URI(url)).httpMethod(POST)
                        .headerMapper(httpHeadersMapper()))
                .handle(biSyncService, "slackResponse").get();
    }
}
