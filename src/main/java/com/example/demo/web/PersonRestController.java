package com.example.demo.web;

import com.example.demo.service.PersonService;
import com.example.demo.web.model.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/person")
@RestController
public class PersonRestController {

    private final PersonService personService;

    @GetMapping(produces = { "application/json"} )
    public ResponseEntity getAllPersons(){
        return new ResponseEntity<List<PersonDto>>(personService.getAll(), HttpStatus.OK);
    }
}
