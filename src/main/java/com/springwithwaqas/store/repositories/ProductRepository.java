package com.springwithwaqas.store.repositories;

import com.springwithwaqas.store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}