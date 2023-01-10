package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa> >getListaPessoas(){
        List<Pessoa> lista = service.getListaPessoas();

        return lista.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista);

    }
}
