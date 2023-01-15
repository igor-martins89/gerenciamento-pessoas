package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.dtos.AddressDTO;

import br.com.martins.igor.backend.services.AddressService;
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
public class AddressControllerTest {

    @Autowired
    AddressController controller;

    @MockBean
    AddressService service;


    @Test
    @DisplayName("When getListAddressesFromPerson is activated, should return a list of Addresses from Person")
    void whenGetListAddressesFromPersonShouldReturnListOfAddresses(){
        List<AddressDTO> list = new ArrayList<>();
        AddressDTO e1 = new AddressDTO();
        AddressDTO e2 = new AddressDTO();

        list.addAll(Arrays.asList(e1, e2));
        when(service.getListAddressesFromPerson(anyInt())).thenReturn(list);

        ResponseEntity<List<AddressDTO>> result = controller.getListAddressesFromPerson(1);
        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());
    }

    @Test
    @DisplayName("When getListAddressesFromPerson is activated, it should return status 204 if empty")
    void whenGetListAddressesFromPersonShouldReturn204StatusIfEmpty(){
        List<AddressDTO> list = new ArrayList<>();
        when(service.getListAddressesFromPerson(anyInt())).thenReturn(list);

        ResponseEntity<List<AddressDTO>> result = controller.getListAddressesFromPerson(1);
        assertEquals(204, result.getStatusCode().value());
    }

    @Test
    @DisplayName("When updating DefaultAddress, the call should return status 200 with a new default address\n")
    void updateDefaultAddressShouldReturnStatus200WithNewDefaultAddress(){

        AddressDTO e = new AddressDTO();
        when(service.updatesDefaultAddress(anyInt(), anyInt())).thenReturn(e);

        ResponseEntity<AddressDTO> result = controller.updatesDefaultAddress(1, 2);

        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());
    }


}
