package com.example.search.controller;

import com.example.common.domain.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
public class SearchController {
    private final RestTemplate restTemplate;

    @Autowired
    public SearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/weather/search")
    @HystrixCommand(fallbackMethod = "defaultResponse")
    public ResponseEntity<String> getDetails() {
        CompletableFuture<?>[] futures = new CompletableFuture[] {
            CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://employee/employee/adults", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForEntity("http://details/details/port", String.class))
        };
        CompletableFuture.allOf(futures).join();
        return new ResponseEntity<>(futures[0].join() + "\n\n" + futures[1].join(), HttpStatus.OK);
    }

    public ResponseEntity<String> defaultResponse() {
        return new ResponseEntity<>("Server is busy....try again later.", HttpStatus.OK);
    }
}
