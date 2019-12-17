package com.example;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {
    private static final AtomicInteger COUNTER = new AtomicInteger();

    @GetMapping("/count")
    public Map<String, Integer> getCount() {
        final int count = COUNTER.incrementAndGet();
        log.info("count: {}", count);
        if (count % 5 == 0) {
            throw new ResponseStatusException(GATEWAY_TIMEOUT);
        }
        return Map.of("count", count);
    }
}
