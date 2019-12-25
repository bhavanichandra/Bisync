package com.bhavanichandra.bisync.config;

import com.bhavanichandra.bisync.service.IBisync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.integration.dsl.IntegrationFlows.from;
import static org.springframework.integration.dsl.MessageChannels.direct;
import static org.springframework.integration.http.dsl.Http.outboundGateway;

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

    @Bean
    public HttpMessageConverter<Object> httpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(asList(APPLICATION_JSON, APPLICATION_FORM_URLENCODED));
        return converter;
    }


//    @Bean
//    public MessageHandler httpGateway() {
//        HttpRequestExecutingMessageHandler httpHandler = new HttpRequestExecutingMessageHandler("https://enwbr1vviftg.x.pipedream.net/");
//        httpHandler.setExpectedResponseType(Map.class);
//        httpHandler.setMessageConverters(singletonList(httpMessageConverter()));
//        httpHandler.setHttpMethod(POST);
//        return httpHandler;
//    }


    @Bean("bisync.request.channel")
    public MessageChannel inputMessageChannel() {
        return direct().get();
    }

    @Bean
    public IntegrationFlow processFlow() throws URISyntaxException {
        return from("bisync.request.channel")
                .handle(biSyncService, "constructSlackMessage")
                .handle(outboundGateway(url).httpMethod(POST).expectedResponseType(Map.class))
                .handle(biSyncService, "slackResponse")
                .get();
    }
}
