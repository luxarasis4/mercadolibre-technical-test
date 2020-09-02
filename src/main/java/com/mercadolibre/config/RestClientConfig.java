
package com.mercadolibre.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
    @Value("${mercadolibre.rest.connect-timeout:30s}")
    private Duration connectTimeout;
    @Value("${mercadolibre.rest.read-timeout:10s}")
    private Duration readTimeout;

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(connectTimeout).setReadTimeout(readTimeout)
                .build();
    }
}
