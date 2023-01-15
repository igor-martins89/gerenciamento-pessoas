package br.com.martins.igor.backend.services;

import br.com.martins.igor.backend.dtos.AddressDTO;
import br.com.martins.igor.backend.entities.Address;
import br.com.martins.igor.backend.entities.Person;
import br.com.martins.igor.backend.entities.enums.TypeAddress;
import br.com.martins.igor.backend.repositories.AddressRepository;
import br.com.martins.igor.backend.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    @Autowired
    PersonService personService;


    public List<AddressDTO> getListAddressesFromPerson(int id) {

        return personService.getPersonById(id).getAddresses().stream().map(x -> convertToDTO(x)).collect(Collectors.toList());
    }

    private AddressDTO convertToDTO(Address obj) {
        return new AddressDTO(obj.getId(), obj.getAddress(), obj.getZipCode(), obj.getNumber(), obj.getCity(), obj.getType());
    }

    private Address convertFromDTO(AddressDTO dto, Person person) {
        return new Address(dto.getId(), dto.getAddress(), dto.getZipCode(), dto.getNumber(), dto.getCity(), dto.getType(), person);
    }

    public Address postAddress(int id, AddressDTO obj) {
        Person person = personService.getPersonById(id);
        obj.setType(person.getAddresses().isEmpty() ? TypeAddress.DEFAULT_ADDRESS.getCod() : TypeAddress.ADITIONAL_ADDRESS.getCod());

        return repository.save(convertFromDTO(obj, person));
    }

    public AddressDTO updatesDefaultAddress(int personId, int addressId) {
        List<Address> addresses = repository.findAddressByPersonId(personId);
        int indexDefaultAddress = -1;
        int indexNewDefaultAddress = -1;
        if (!addresses.isEmpty()) {
            for (int i = 0; i < addresses.size(); i++) {
                if (addresses.get(i).getType() == TypeAddress.DEFAULT_ADDRESS.getCod()) {
                    indexDefaultAddress = i;
                }
                if (addresses.get(i).getId() == addressId) {
                    indexNewDefaultAddress = i;
                }
            }
            if (indexNewDefaultAddress > -1) {
                addresses.get(indexDefaultAddress).setType(TypeAddress.ADITIONAL_ADDRESS.getCod());
                addresses.get(indexNewDefaultAddress).setType(TypeAddress.DEFAULT_ADDRESS.getCod());
                repository.saveAll(addresses);
                return convertToDTO(addresses.get(indexNewDefaultAddress));
            }
        }
        throw new EntityNotFoundException("Address id " + addressId + " not found ");
    }
}