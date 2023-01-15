package br.com.martins.igor.backend.services;

import br.com.martins.igor.backend.dtos.EnderecoDTO;
import br.com.martins.igor.backend.entities.Endereco;
import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.entities.enums.TipoEndereco;
import br.com.martins.igor.backend.repositories.EnderecoRepository;
import br.com.martins.igor.backend.services.exceptions.EntityNotFoundException;
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

        return pessoaService.getPessoaPorId(id).getEnderecos().stream().map(x -> converterParaDTO(x)).collect(Collectors.toList());
    }

    private EnderecoDTO converterParaDTO(Endereco obj) {
        return new EnderecoDTO(obj.getId(), obj.getLogradouro(), obj.getCep(), obj.getNumero(), obj.getCidade(), obj.getTipo());
    }

    private Endereco converterDeDTO(EnderecoDTO dto, Pessoa pessoa) {
        return new Endereco(dto.getId(), dto.getLogradouro(), dto.getCep(), dto.getNumero(), dto.getCidade(), dto.getTipo(), pessoa);
    }

    public Endereco cadastraEndereco(int id, EnderecoDTO obj) {
        Pessoa pessoa = pessoaService.getPessoaPorId(id);
        obj.setTipo(pessoa.getEnderecos().isEmpty() ? TipoEndereco.ENDERECO_PADRAO.getCod() : TipoEndereco.ENDERECO_ADICIONAL.getCod());

        return repository.save(converterDeDTO(obj, pessoa));
    }

    public EnderecoDTO atualizaEnderecoPadrao(int idPessoa, int idEndereco) {
        List<Endereco> enderecos = repository.findEnderecoByPessoaId(idPessoa);
        int indiceEnderecoPadrao = -1;
        int indiceNovoEnderecoPadrao = -1;
        if (!enderecos.isEmpty()) {
            for (int i = 0; i < enderecos.size(); i++) {
                if (enderecos.get(i).getTipo() == TipoEndereco.ENDERECO_PADRAO.getCod()) {
                    indiceEnderecoPadrao = i;
                }
                if (enderecos.get(i).getId() == idEndereco) {
                    indiceNovoEnderecoPadrao = i;
                }
            }
            if (indiceNovoEnderecoPadrao > -1) {
                enderecos.get(indiceEnderecoPadrao).setTipo(TipoEndereco.ENDERECO_ADICIONAL.getCod());
                enderecos.get(indiceNovoEnderecoPadrao).setTipo(TipoEndereco.ENDERECO_PADRAO.getCod());
                repository.saveAll(enderecos);
                return converterParaDTO(enderecos.get(indiceNovoEnderecoPadrao));
            }
        }
        throw new EntityNotFoundException("Address id " + idEndereco + " not found ");
    }
}