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

    @Autowired
    PessoaService pessoaService;


    public List<Endereco> getListaEnderecosDePessoa(int id) {
        return pessoaService.getPessoaPorId(id) != null ? repository.findEnderecoByPessoaId(id) : null;
    }

    public Endereco cadastraEndereco(int id,Endereco obj) {
        Pessoa pessoa = pessoaService.getPessoaPorId(id);
        if(pessoa != null){
            obj.setId(null);
            obj.setPessoa(pessoa);
            obj = repository.save(obj);
            return obj;
        }
        return null;
    }
}
