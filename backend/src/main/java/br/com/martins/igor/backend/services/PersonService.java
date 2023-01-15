package br.com.martins.igor.backend.services;

import br.com.martins.igor.backend.dtos.PersonDTO;
import br.com.martins.igor.backend.entities.Person;
import br.com.martins.igor.backend.repositories.PersonRepository;
import br.com.martins.igor.backend.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public Page getListPeople(String name, int page, int linesPerPage, String orderBy, String direction) {
        Pageable paginacao = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<Person> people = name.length() > -1 ? repository.findByNameContainingIgnoreCase(paginacao, name) : repository.findAll(paginacao);

        return PersonDTO.conversor(people);
    }

    private static Page<PersonDTO> conversor(Page<Person> people){
        return people.map(PersonDTO::new);
    }

    public Person getPersonById(int id) {
        return existsById(id);

    }

    private Person existsById(int id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Person id " + id + " not found");
        }
        return repository.findById(id).orElse(null);
    }

    public Person postPerson(PersonDTO obj) {
        Person person = repository.save(convertFromDTO(obj));
        return person;
    }

    private Person convertFromDTO(PersonDTO dto){
        return new Person(null, dto.getName(), dto.getBirthDate(), null);
    }



    public PersonDTO updatePerson(int id, PersonDTO obj) {
        Person person = existsById(id);
        person.setName(obj.getName());
        person.setBirthDate(obj.getBirthDate());
        return new PersonDTO(repository.save(person));

    }
}
