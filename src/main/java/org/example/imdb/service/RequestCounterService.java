package org.example.imdb.service;

import java.util.concurrent.atomic.AtomicLong;

public interface RequestCounterService {

    void incrementRequestCount();
    AtomicLong getRequestCount();
}


