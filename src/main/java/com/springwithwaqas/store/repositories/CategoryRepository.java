package com.springwithwaqas.store.repositories;

import com.springwithwaqas.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}