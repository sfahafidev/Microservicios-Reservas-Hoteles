package com.aleal.hotels.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean("clientRest")
    public RestClient registreRestClient() {
        return RestClient.create();
    }

}
