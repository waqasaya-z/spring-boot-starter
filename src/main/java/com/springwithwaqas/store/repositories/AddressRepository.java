package com.springwithwaqas.store.repositories;

import com.springwithwaqas.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}