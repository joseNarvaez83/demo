package com.example.demo.bootstrap;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Profile("default || local || mysql")
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
        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder()
                .id(UUID.randomUUID())
                .firstName("Jose")
                .lastName("Narvaez")
                .build());

        persons.add(Person.builder()
                .id(UUID.randomUUID())
                .firstName("Juan")
                .lastName("Jasso")
                .build());

        persons.add(Person.builder()
                .id(UUID.randomUUID())
                .firstName("Mario")
                .lastName("Molina")
                .build());

        persons.add(Person.builder()
                .id(UUID.randomUUID())
                .firstName("Peter")
                .lastName("Pintado")
                .build());

        persons.add(Person.builder()
                .id(UUID.randomUUID())
                .firstName("Alex")
                .lastName("Alvarez")
                .build());


        personRepository.saveAll(persons);
    }
}
