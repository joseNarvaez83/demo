package com.example.demo.web;

import com.example.demo.service.PersonService;
import com.example.demo.web.model.PersonDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

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

        given(personService.getAll()).willReturn(getPersonListDto());

        mockMvc.perform(get("/api/v1/person"))
                .andExpect(status().isOk());
    }

    private List<PersonDto> getPersonListDto() {
        return Arrays.asList(getPersonDto());
    }

    private PersonDto getPersonDto() {
        return PersonDto.builder()
            .id(UUID.randomUUID())
            .version(0)
            .createdDate(OffsetDateTime.now())
            .lastModifiedDate(OffsetDateTime.now())
            .firstName("Pepe")
            .lastName("Pecas")
            .build();
    }
}