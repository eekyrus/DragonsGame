package com.eek.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;

@Configuration
public class RestTemplateConfig {

    @Bean("domRestTemplate")
    public RestTemplate domRestTemplate() {
        UriBuilderFactory defaultUriTemplateHandler = new DefaultUriBuilderFactory("https://dragonsofmugloar.com/api/v2/");
        return new RestTemplateBuilder().uriTemplateHandler(defaultUriTemplateHandler).build();
    }

}
