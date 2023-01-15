package br.com.martins.igor.backend.controllers;

import br.com.martins.igor.backend.dtos.AddressDTO;
import br.com.martins.igor.backend.entities.Address;
import br.com.martins.igor.backend.services.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/addresses")
@Api("Address REST API")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    AddressService service;

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a list of addresses for a person")
    public ResponseEntity<List<AddressDTO>> getListAddressesFromPerson(@PathVariable int id){
        List<AddressDTO> addresses = service.getListAddressesFromPerson(id);

        return addresses.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(addresses);
    }

    @PostMapping("/{personId}")
    @ApiOperation(value = "Register a new address")
    public ResponseEntity<Void> postAddress(@PathVariable int personId, @Valid @RequestBody AddressDTO obj){

        Address address = service.postAddress(personId, obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{personId}").buildAndExpand(address.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{personId}/{addressId}")
    @ApiOperation(value = "Updates the person's default address")
    public ResponseEntity<AddressDTO> updatesDefaultAddress(@PathVariable int personId, @PathVariable int addressId){
        AddressDTO newDefaultAddress = service.updatesDefaultAddress(personId, addressId);

        return ResponseEntity.status(200).body(newDefaultAddress);
    }
}
