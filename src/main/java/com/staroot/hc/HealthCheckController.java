package com.staroot.hc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;

    @GetMapping("/healthcheck")
    public List<CompletableFuture<HealthCheckResultEntity>> healthCheck() throws IOException {
        List<String> urls = healthCheckService.getUrlsFromFile("urls.txt");
        return urls.stream().map(healthCheckService::checkURL).collect(Collectors.toList());
    }
}
