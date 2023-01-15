package br.com.martins.igor.backend.repositories;

import br.com.martins.igor.backend.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Page<Person> findByNameContainingIgnoreCase(Pageable pageable, String name);
}
