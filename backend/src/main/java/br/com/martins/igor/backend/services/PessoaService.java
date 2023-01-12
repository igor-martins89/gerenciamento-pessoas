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

    public Pessoa getPessoaPorId(int id) {
        return existePorId(id);

    }

    private Pessoa existePorId(int id){
        return repository.findById(id).orElse(null);
    }

    public Pessoa cadastraPessoa(Pessoa obj) {
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }

    public Pessoa editarPessoa(int id, Pessoa obj) {
        Pessoa pessoa = existePorId(id);
        if(pessoa != null){
            pessoa.setNome(obj.getNome());
            pessoa.setDataNascimento(obj.getDataNascimento());
            return repository.save(pessoa);
        }
        return null;
    }
}
