package com.example.demo.bootstrap;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class PersonLoader implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        if (personRepository.count() == 0) {
            log.debug("Person loader was executed, added initial data!");
            personsInit();
        }
    }

    private void personsInit() {
        personRepository.saveAndFlush(Person.builder()
                .id(UUID.randomUUID())
                .firstName("Jose")
                .lastName("Narvaez")
                .build()
        );
    }
}
