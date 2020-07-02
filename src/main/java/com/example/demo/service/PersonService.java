package com.example.demo.service;

import com.example.demo.web.model.PersonDto;
import com.example.demo.web.model.PersonPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PersonService {

    PersonPagedList getAll(PageRequest pageRequest);

    PersonDto savePerson(PersonDto personDto);
}
