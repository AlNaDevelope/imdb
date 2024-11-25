package org.example.imdb.controller;


import org.example.imdb.service.RequestCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/request")
public class RequestCountController {

    private final RequestCounterService requestCounterService;

    @Autowired
    public RequestCountController(RequestCounterService requestCounterService) {
        this.requestCounterService = requestCounterService;
    }

    @RequestMapping("/requestCount")
    @GetMapping
    public AtomicLong getRequestCount() {
        return requestCounterService.getRequestCount();
    }
}
