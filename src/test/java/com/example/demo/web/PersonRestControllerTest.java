package com.example.demo.web;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.web.controllers.PersonRestController;
import com.example.demo.web.model.PersonDto;
import com.example.demo.web.model.PersonPagedList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonRestController.class)
class PersonRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PersonService personService;

    @Test
    void getAllPersons() throws Exception {

        given(personService.getAll(any())).willReturn(
                new PersonPagedList(getPersonListDto(),
                        PageRequest
                        .of(1, 1),
                        10L)
        );

        mockMvc.perform(get("/api/v1/person"))
                .andExpect(status().isOk());
    }

    @Test
    void savePersonTest() throws Exception {
        PersonDto personDto = getPersonDto();
        String json = objectMapper.writeValueAsString(personDto);

        mockMvc.perform(post("/api/v1/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated()
        );

    }

    private List<PersonDto> getPersonListDto() {
        return Arrays.asList(getPersonDto());
    }

    private PersonDto getPersonDto() {
        return PersonDto.builder()
            .firstName("Pepe")
            .lastName("Pecas")
            .build();
    }
}