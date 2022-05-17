package com.eek.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;

@Configuration
public class RestTemplateConfig {

  @Value("${dragons.of.mugloar.rest.url}")
  private String url;

  @Bean("domRestTemplate")
  public RestTemplate domRestTemplate() {
    UriBuilderFactory defaultUriTemplateHandler = new DefaultUriBuilderFactory(url);
    return new RestTemplateBuilder().uriTemplateHandler(defaultUriTemplateHandler).build();
  }

}
