package com.springwithwaqas.store.repositories;

import com.springwithwaqas.store.entities.Product;
import com.springwithwaqas.store.entities.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.springwithwaqas.store.dtos.ProductSummary;
import com.springwithwaqas.store.dtos.ProductSummaryDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameNotLike(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByPriceBetweenAndPriceGreaterThan(BigDecimal min, BigDecimal max, BigDecimal price);
    List<Product> findByPriceBetweenAndPriceLessThan(BigDecimal min, BigDecimal max, BigDecimal price);

    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionLike(String description);
    List<Product> findByDescriptionNotLike(String description);

    List<Product> findByNameOrderByPriceDesc(String name);

    List<Product> findTop5ByNameOrderByPriceDesc(String name);

    // Find products whose price are in a given range and sort by name
    @Procedure("findProductByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max")  BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

   @Query("select new com.springwithwaqas.store.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
   List<ProductSummaryDTO> findByCategory(@Param("category") Category category);
}