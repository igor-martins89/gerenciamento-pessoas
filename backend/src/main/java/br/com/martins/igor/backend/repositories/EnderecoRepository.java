package br.com.martins.igor.backend.repositories;

import br.com.martins.igor.backend.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    List<Endereco> findEnderecoByPessoaId(int id);
}
