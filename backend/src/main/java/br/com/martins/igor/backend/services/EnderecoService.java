package br.com.martins.igor.backend.services;

import br.com.martins.igor.backend.entities.Endereco;
import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public List<Endereco> postEnderecos(Pessoa pessoa) {
        for(Endereco e : pessoa.getEnderecos()){
            e.setId(null);
            e.setPessoa(pessoa);
        }
        return repository.saveAll(pessoa.getEnderecos());
    }
}
