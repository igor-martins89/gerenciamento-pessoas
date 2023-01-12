package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/pessoas")
@Api("API REST Pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    PessoaService service;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de pessoas")
    public ResponseEntity<List<Pessoa> >getListaPessoas(){
        List<Pessoa> lista = service.getListaPessoas();

        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna a pessoa respectiva do ID passado, caso exista")
    public ResponseEntity<Pessoa> getPessoaPorId(@PathVariable int id){
        Pessoa pessoa = service.getPessoaPorId(id);
        return pessoa != null ? ResponseEntity.ok().body(pessoa) : ResponseEntity.status(404).build();
    }

    @PostMapping
    @ApiOperation(value = "Cadastra uma nova pessoa")
    public ResponseEntity<Void> cadastraPessoa(@RequestBody Pessoa obj){
        Pessoa pessoa = service.cadastraPessoa(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Edita informações de uma pessoa")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable int id, @RequestBody Pessoa obj){
        Pessoa pessoa = service.editarPessoa(id, obj);

        return pessoa != null ? ResponseEntity.status(200).body(pessoa) : ResponseEntity.status(404).build();
    }

}
