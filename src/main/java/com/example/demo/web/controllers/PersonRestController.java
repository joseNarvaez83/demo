package com.example.demo.web.controllers;

import com.example.demo.service.PersonService;
import com.example.demo.web.model.PersonDto;
import com.example.demo.web.model.PersonPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/person")
@RestController
public class PersonRestController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private final PersonService personService;

    @GetMapping(produces = { "application/json"} )
    public ResponseEntity<PersonPagedList> getAllPersons(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ){
        if( pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if( pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        PersonPagedList pagedList = personService.getAll(PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(pagedList, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity savePerson(@RequestBody @Validated PersonDto personDto){
        return new ResponseEntity(personService.savePerson(personDto), HttpStatus.CREATED);
    }
}
