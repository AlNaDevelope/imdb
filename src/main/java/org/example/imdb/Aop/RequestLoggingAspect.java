package org.example.imdb.Aop;

import org.aspectj.lang.annotation.*;
import org.example.imdb.service.RequestCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLoggingAspect {

    private final RequestCounterService requestCounterService;

    @Autowired
    public RequestLoggingAspect(RequestCounterService requestCounterService) {
        this.requestCounterService = requestCounterService;
    }

    @Pointcut("execution(@org.springframework.web.bind.annotation.GetMapping * *.*(..)) || execution(@org.springframework.web.bind.annotation.PostMapping * *.*(..)) || execution(@org.springframework.web.bind.annotation.PutMapping * *.*(..)) || execution(@org.springframework.web.bind.annotation.DeleteMapping * *.*(..))")
    public void apiMethods() {}

    @Before("apiMethods()")
    public void logRequest() {
        this.requestCounterService.incrementRequestCount();
    }
}