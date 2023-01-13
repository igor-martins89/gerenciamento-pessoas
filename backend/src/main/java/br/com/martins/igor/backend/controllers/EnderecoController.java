package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.dtos.EnderecoDTO;
import br.com.martins.igor.backend.entities.Endereco;
import br.com.martins.igor.backend.services.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/enderecos")
@Api("API REST Endereços")
@CrossOrigin(origins = "*")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna uma lista de endereços de uma pessoa")
    public ResponseEntity<List<EnderecoDTO>> getListaEnderecosDePessoa(@PathVariable int id){
        List<EnderecoDTO> enderecos = service.getListaEnderecosDePessoa(id);

        return enderecos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(enderecos);
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "Cadastra um novo endereço")
    public ResponseEntity<Void> cadastraEndereco(@PathVariable int id, @RequestBody EnderecoDTO obj){

        Endereco endereco = service.cadastraEndereco(id, obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(endereco.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}