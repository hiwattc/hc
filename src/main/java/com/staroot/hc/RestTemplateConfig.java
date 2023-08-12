package com.staroot.hc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        int timeout = 10000; // 5초 타임아웃 설정

        // SimpleClientHttpRequestFactory를 사용하여 타임아웃 설정
        ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        ((SimpleClientHttpRequestFactory) factory).setConnectTimeout(timeout);
        ((SimpleClientHttpRequestFactory) factory).setReadTimeout(timeout);

        return new RestTemplate(factory);
    }
}
