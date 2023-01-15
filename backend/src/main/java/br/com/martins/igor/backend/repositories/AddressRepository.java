package br.com.martins.igor.backend.repositories;

import br.com.martins.igor.backend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAddressByPersonId(int id);

    Optional<Address> findAddressByIdAndPersonId(int addressId, int personId);
}
