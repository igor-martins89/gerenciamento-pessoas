package br.com.martins.igor.backend.services;

import br.com.martins.igor.backend.dtos.EnderecoDTO;
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


    public List<EnderecoDTO> getListaEnderecosDePessoa(int id) {
        return pessoaService.getPessoaPorId(id) != null ? repository.findEnderecoByPessoaId(id).stream().map(x -> converterParaDTO(x)).collect(Collectors.toList()) : null;
    }

    private EnderecoDTO converterParaDTO(Endereco obj){
        return new EnderecoDTO(obj.getId(), obj.getLogradouro(), obj.getCep(), obj.getNumero(), obj.getCidade());
    }

    private Endereco converterDeDTO(EnderecoDTO dto, Pessoa pessoa){
        return new Endereco(dto.getId(), dto.getLogradouro(), dto.getCep(), dto.getNumero(), dto.getCidade(), pessoa);
    }

    public Endereco cadastraEndereco(int id,EnderecoDTO obj) {
        Pessoa pessoa = pessoaService.getPessoaPorId(id);

        return pessoa != null ? repository.save(converterDeDTO(obj, pessoa)) : null;
    }
}
