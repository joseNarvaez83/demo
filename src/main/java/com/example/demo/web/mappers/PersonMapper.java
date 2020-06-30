package com.example.demo.web.mappers;

import com.example.demo.model.Person;
import com.example.demo.web.model.PersonDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface PersonMapper {

    PersonDto personToPersonDto(Person person);
    Person personDtoToPerson(PersonDto personDto);

}
