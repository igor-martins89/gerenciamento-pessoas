package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.dtos.PersonDTO;
import br.com.martins.igor.backend.entities.Person;
import br.com.martins.igor.backend.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/people")
@Api("People REST API")
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping
    @ApiOperation(value = "Returns a list of people")
    public Page<PersonDTO>getListPeople(
            @RequestParam(value="name", defaultValue="") String name,
            @RequestParam(value="page", defaultValue="0") int page,
            @RequestParam(value="linesPerPage", defaultValue="8") int linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction
    ){
        return service.getListPeople(name, page, linesPerPage, orderBy, direction);
    }



    @GetMapping("/{id}")
    @ApiOperation(value = "Returns the respective person of the passed ID, if exists")
    public ResponseEntity<Person> getPersonById(@PathVariable int id){
        Person person = service.getPersonById(id);
        return ResponseEntity.ok().body(person);
    }

    @PostMapping
    @ApiOperation(value = "Register a new person")
    public ResponseEntity<Void> postPerson(@Valid @RequestBody PersonDTO obj){
        Person person = service.postPerson(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(person.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Edit a person's information\n")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable int id, @Valid @RequestBody PersonDTO obj){
        PersonDTO person = service.updatePerson(id, obj);

        return person != null ? ResponseEntity.status(200).body(person) : ResponseEntity.status(404).build();
    }

}
