package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.dtos.PessoaDTO;
import br.com.martins.igor.backend.entities.Pessoa;
import br.com.martins.igor.backend.repositories.PessoaRepository;
import br.com.martins.igor.backend.services.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PessoaControllerTest {

    @Autowired
    PessoaController controller;

    @MockBean
    PessoaRepository repository;

    @Test
    @DisplayName("Quando ID de Pessoa existir no banco deverá retornar Pessoa")
    void getPessoaPorIdExistirDeverarRetornarPessoa(){
        Pessoa pessoa = new Pessoa();
        when(this.repository.existsById(anyInt())).thenReturn(true);
        when(this.repository.findById(anyInt())).thenReturn(Optional.of(pessoa));

        ResponseEntity<Pessoa> resultado = controller.getPessoaPorId(1);

        assertEquals(200, resultado.getStatusCode().value());
        assertNotNull(resultado.getBody());

    }

    @Test
    @DisplayName("Quando ID de Pessoa não existir no banco deverá retornar um EntityNotFoundException")
    void getPessoaPorIdNaoExistirDeveraRetornarEntityNotFoundException(){
        when(this.repository.existsById(anyInt())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> controller.getPessoaPorId(1));
    }

    @Test
    @DisplayName("Quando cadastro de Pessoa for aceito deverá retornar um status 201")
    void postPessoaAceitoDeveraRetornarStatus201(){
        Pessoa pessoa = new Pessoa();
        PessoaDTO dto = new PessoaDTO();
        when(repository.save(any())).thenReturn(pessoa);

        ResponseEntity<Void> resultado = controller.cadastraPessoa(dto);

        assertEquals(201, resultado.getStatusCode().value());
    }

}
