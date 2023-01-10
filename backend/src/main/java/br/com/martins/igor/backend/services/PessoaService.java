package br.com.martins.igor.backend.services;

import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public List<Pessoa> getListaPessoas() {
        List<Pessoa> lista = repository.findAll();

        return lista.isEmpty() ? null : lista;
    }
}
