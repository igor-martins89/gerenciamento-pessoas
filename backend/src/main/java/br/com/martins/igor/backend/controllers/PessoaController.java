package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.dtos.PessoaDTO;
import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public Page<PessoaDTO>getListaPessoas(
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value="pagina", defaultValue="0") int pagina,
            @RequestParam(value="linhasPorPagina", defaultValue="8") int linhasPorPagina,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direcao
    ){
        return service.getListaPessoas(nome, pagina, linhasPorPagina, orderBy, direcao);
    }



    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna a pessoa respectiva do ID passado, caso exista")
    public ResponseEntity<Pessoa> getPessoaPorId(@PathVariable int id){
        Pessoa pessoa = service.getPessoaPorId(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra uma nova pessoa")
    public ResponseEntity<Void> cadastraPessoa(@Valid @RequestBody PessoaDTO obj){
        int idPessoa = service.cadastraPessoa(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(idPessoa).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Edita informações de uma pessoa")
    public ResponseEntity<PessoaDTO> editarPessoa(@PathVariable int id, @Valid @RequestBody PessoaDTO obj){
        PessoaDTO pessoa = service.editarPessoa(id, obj);

        return pessoa != null ? ResponseEntity.status(200).body(pessoa) : ResponseEntity.status(404).build();
    }

}
