package com.springwithwaqas.store.repositories;

import com.springwithwaqas.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
