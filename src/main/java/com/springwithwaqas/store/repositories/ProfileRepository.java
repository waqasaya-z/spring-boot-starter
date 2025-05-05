package com.springwithwaqas.store.repositories;

import com.springwithwaqas.store.entities.Profile;
import com.springwithwaqas.store.dtos.UserSummary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}