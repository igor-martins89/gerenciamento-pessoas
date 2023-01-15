package br.com.martins.igor.backend.services;

import br.com.martins.igor.backend.dtos.PessoaDTO;
import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.repositories.PessoaRepository;
import br.com.martins.igor.backend.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public Page getListaPessoas(String nome, int pagina, int linhasPorPagina, String orderBy, String direcao) {
        Pageable paginacao = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), orderBy);

        Page<Pessoa> pessoas = nome.length() > -1 ? repository.findByNomeContainingIgnoreCase(paginacao, nome) : repository.findAll(paginacao);

        return PessoaDTO.conversor(pessoas);
    }

    private static Page<PessoaDTO> conversor(Page<Pessoa> pessoas){
        return pessoas.map(PessoaDTO::new);
    }

    public Pessoa getPessoaPorId(int id) {
        return existePorId(id);

    }

    private Pessoa existePorId(int id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person id " + id + " not found "));
    }

    public int cadastraPessoa(PessoaDTO obj) {
        Pessoa pessoa = repository.save(converterDeDTO(obj));
        return pessoa.getId();
    }

    private Pessoa converterDeDTO(PessoaDTO dto){
        return new Pessoa(null, dto.getNome(), dto.getDataNascimento(), null);
    }



    public PessoaDTO editarPessoa(int id, PessoaDTO obj) {
        Pessoa pessoa = existePorId(id);
        if(pessoa != null){
            pessoa.setNome(obj.getNome());
            pessoa.setDataNascimento(obj.getDataNascimento());
            return new PessoaDTO(repository.save(pessoa));
        }
        return null;
    }
}
