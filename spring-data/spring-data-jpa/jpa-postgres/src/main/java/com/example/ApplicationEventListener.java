package com.example;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final PersonRepository personRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        List<Person> persons = personRepository.findAll();
        log.info("{}", persons);
    }
}