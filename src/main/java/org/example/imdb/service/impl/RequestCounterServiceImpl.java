package org.example.imdb.service.impl;

import org.example.imdb.service.RequestCounterService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class RequestCounterServiceImpl implements RequestCounterService {


    private AtomicLong requestCount = new AtomicLong(0);

    @Override
    public void incrementRequestCount() {
        requestCount.incrementAndGet();
    }

    @Override
    public AtomicLong getRequestCount() {
        return requestCount;
    }
}
