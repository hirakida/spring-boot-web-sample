package com.example;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createTest() {
        User user = new User(null, "name1", 30, LocalDateTime.now(), LocalDateTime.now());
        Mono<User> save = userRepository.save(user);

        StepVerifier.create(save)
                    .expectNextMatches(created -> created.getId() != null
                                                  && "name1".equals(created.getName()))
                    .verifyComplete();
    }
}