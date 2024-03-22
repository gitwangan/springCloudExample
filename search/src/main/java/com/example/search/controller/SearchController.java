package com.example.search.controller;

import com.example.common.domain.Employee;
import com.example.common.domain.GeneralResponse;
import com.example.common.domain.SearchResponseData;
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
    public ResponseEntity<SearchResponseData> getDetails() {
        CompletableFuture<Object>[] futures = new CompletableFuture[] {
            CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://employee/employee/adults", Employee[].class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://details/details/port", GeneralResponse.class))
        };
        CompletableFuture.allOf(futures).join();
        SearchResponseData searchResponseData = new SearchResponseData();
        searchResponseData.setEmployeeResponse((Employee[]) futures[0].join());
        searchResponseData.setPortResponse((String) ((GeneralResponse) futures[1].join()).getData());
        return new ResponseEntity<>(searchResponseData, HttpStatus.OK);
    }

    public ResponseEntity<String> defaultResponse() {
        return new ResponseEntity<>("Server is busy....try again later.", HttpStatus.OK);
    }
}
