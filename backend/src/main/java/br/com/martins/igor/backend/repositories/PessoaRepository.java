package br.com.martins.igor.backend.repositories;

import br.com.martins.igor.backend.entities.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Page<Pessoa> findByNomeContainingIgnoreCase(Pageable paginacao, String nome);
}
