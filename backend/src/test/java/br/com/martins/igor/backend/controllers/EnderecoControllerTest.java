package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.dtos.EnderecoDTO;

import br.com.martins.igor.backend.entities.Endereco;
import br.com.martins.igor.backend.repositories.EnderecoRepository;
import br.com.martins.igor.backend.repositories.PessoaRepository;
import br.com.martins.igor.backend.services.EnderecoService;
import br.com.martins.igor.backend.services.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EnderecoControllerTest {

    @Autowired
    EnderecoController controller;

    @MockBean
    EnderecoService service;


    @Test
    @DisplayName("Quando getListaEnderecosDePessoa acionado deverá retornar lista de Endereços da Pessoa")
    void getListaEnderecosDePessoaDeveraRetornarListaDeEnderecos(){
        List<EnderecoDTO> lista = new ArrayList<>();
        EnderecoDTO e1 = new EnderecoDTO();
        EnderecoDTO e2 = new EnderecoDTO();

        lista.addAll(Arrays.asList(e1, e2));
        when(service.getListaEnderecosDePessoa(anyInt())).thenReturn(lista);

        ResponseEntity<List<EnderecoDTO>> resultado = controller.getListaEnderecosDePessoa(1);
        assertEquals(200, resultado.getStatusCode().value());
        assertNotNull(resultado.getBody());
    }

    @Test
    @DisplayName("Quando getListaEnderecosDePessoa acionado deverá retornar status 204 quando vazia")
    void getListaEnderecosDePessoaDeveraRetornarStatus204QuandoVazia(){
        List<EnderecoDTO> lista = new ArrayList<>();
        when(service.getListaEnderecosDePessoa(anyInt())).thenReturn(lista);

        ResponseEntity<List<EnderecoDTO>> resultado = controller.getListaEnderecosDePessoa(1);
        assertEquals(204, resultado.getStatusCode().value());
    }

    @Test
    @DisplayName("Quando atualizaEnderecoPadrao chamado deverá retornar status 200 com endereço padrão novo")
    void atualizaEnderecoPadraoDeveraRetornarStatus200ComEnderecoPadraoNovo(){

        EnderecoDTO e = new EnderecoDTO();
        when(service.atualizaEnderecoPadrao(anyInt(), anyInt())).thenReturn(e);

        ResponseEntity<EnderecoDTO> resultado = controller.atualizaEnderecoPadrao(1, 2);

        assertEquals(200, resultado.getStatusCode().value());
        assertNotNull(resultado.getBody());
    }


}
