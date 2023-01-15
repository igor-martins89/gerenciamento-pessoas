package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.dtos.PersonDTO;
import br.com.martins.igor.backend.entities.Person;
import br.com.martins.igor.backend.repositories.PersonRepository;
import br.com.martins.igor.backend.services.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonControllerTest {

    @Autowired
    PersonController controller;

    @MockBean
    PersonRepository repository;

    @Test
    @DisplayName("When person id exists in Database, should return Person")
    void getPersonByIdShouldReturnPerson(){
        Person person = new Person();
        when(this.repository.existsById(anyInt())).thenReturn(true);
        when(this.repository.findById(anyInt())).thenReturn(Optional.of(person));

        ResponseEntity<Person> result = controller.getPersonById(1);

        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());

    }

    @Test
    @DisplayName("When person id not exists in database should return a EntityNotFoundException")
    void getPersonByIdNotExistsShouldReturnEntityNotFoundException(){
        when(this.repository.existsById(anyInt())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> controller.getPersonById(1));
    }

    @Test
    @DisplayName("When person registration is accepted should return status 201")
    void postPersonAcceptedShouldReturnStatus201(){
        Person person = new Person();
        PersonDTO dto = new PersonDTO();
        when(repository.save(any())).thenReturn(person);

        ResponseEntity<Void> result = controller.postPerson(dto);

        assertEquals(201, result.getStatusCode().value());
    }

}
