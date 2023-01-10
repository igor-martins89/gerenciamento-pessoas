package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return lista.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista);

    }
}
