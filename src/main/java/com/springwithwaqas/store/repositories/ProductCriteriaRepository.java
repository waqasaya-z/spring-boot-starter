package com.springwithwaqas.store.repositories;
import com.springwithwaqas.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;


public interface ProductCriteriaRepository {
    List<Product> findProductByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
