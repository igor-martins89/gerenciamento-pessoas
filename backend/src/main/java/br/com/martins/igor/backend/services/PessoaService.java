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

    @Autowired
    EnderecoService enderecoService;

    public List<Pessoa> getListaPessoas() {
        List<Pessoa> lista = repository.findAll();

        return lista.isEmpty() ? null : lista;
    }

    public Pessoa getPessoaPorId(int id) {
        return existePorId(id);

    }

    private Pessoa existePorId(int id){
        return repository.findById(id).orElse(null);
    }

    public Pessoa postPessoa(Pessoa obj) {
        obj.setId(null);
        obj = repository.save(obj);
        enderecoService.postEnderecos(obj);
        return obj;
    }
}
