package com.staroot.hc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class HealthCheckService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Logger logger = LoggerFactory.getLogger(HealthCheckService.class);
    @Autowired
    private HealthCheckResultRepository healthCheckResultRepository;

    @Async
    //@Transactional
    public CompletableFuture<HealthCheckResultEntity> checkURL(String url) {
        HealthCheckResultEntity resultEntity;
        try {
            //String response = restTemplate.getForObject(url, String.class);
            //String truncatedResponse = truncateString(response, 1000);

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            HttpStatus httpStatus = (HttpStatus) responseEntity.getStatusCode();


            String responseBody = responseEntity.getBody();
            String truncatedResponse = truncateString(responseBody, 100); // 최대 1000자로 자름


            logger.debug("url : "+url);
            logger.debug("response code : "+httpStatus.toString());
            //logger.debug("responseBody : "+responseBody.toString());


            //resultEntity = new HealthCheckResultEntity(url, httpStatus.toString(), truncatedResponse);
            resultEntity = new HealthCheckResultEntity(url, httpStatus.toString());
        } catch (Exception e) {
            String truncatedResponse = truncateString(e.getMessage(), 100); // 최대 1000자로 자름
            resultEntity = new HealthCheckResultEntity(url, "Error", e.getMessage());
        }
        healthCheckResultRepository.save(resultEntity);
        return CompletableFuture.completedFuture(resultEntity);
    }
    private String truncateString(String input, int maxLength) {
        return input.length() > maxLength ? input.substring(0, maxLength) : input;
    }
    public List<String> getUrlsFromFile(String filePath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}
