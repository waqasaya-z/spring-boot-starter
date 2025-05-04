package com.springwithwaqas.store.repositories;

import com.springwithwaqas.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}