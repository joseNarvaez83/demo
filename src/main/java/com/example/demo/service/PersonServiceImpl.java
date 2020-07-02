package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.web.mappers.PersonMapper;
import com.example.demo.web.model.PersonDto;
import com.example.demo.web.model.PersonPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonPagedList getAll(PageRequest pageRequest) {

        PersonPagedList pagedList;
        Page<Person> personPage;

        personPage = personRepository.findAll(pageRequest);

        pagedList = new PersonPagedList(
                personPage.getContent().stream()
                .map(personMapper::personToPersonDto)
                .collect(Collectors.toList()),
                PageRequest
                    .of(personPage.getPageable().getPageNumber(),
                            personPage.getPageable().getPageSize()),
                personPage.getTotalElements()
        );

        return pagedList;
    }

    @Override
    public PersonDto savePerson(PersonDto personDto) {
        return personMapper
                .personToPersonDto(
                        personRepository.save(
                                personMapper.personDtoToPerson(personDto)
                        )
                );
    }
}
