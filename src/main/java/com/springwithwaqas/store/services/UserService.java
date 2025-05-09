package com.springwithwaqas.store.services;

import com.springwithwaqas.store.entities.Address;
import com.springwithwaqas.store.entities.User;
import com.springwithwaqas.store.entities.Product;
import com.springwithwaqas.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.springwithwaqas.store.repositories.specifications.ProductSpec;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("John")
                .email("john@gmail.com")
                .password("password")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }
    }

    @Transactional
    public void showRelatedEntities() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address.getCity());
    }

    public void persistRelatedEntity() {
        var user = User.builder()
                .name("John")
                .email("john@gmail.com")
                .password("password")
                .build();

        var address = Address.builder()
                .city("London")
                .state("MS")
                .street("TS")
                .zip("65200")
                .build();

        user.addAddress(address);

        userRepository.save(user);

    }

//    @Transactional
//    public void deleteRelatedEntity() {
//       var user =  userRepository.findById(6L).orElseThrow();
//       var address = user.getAddresses().getFirst();
//       user.removeAddress(address);
//    }

    @Transactional
    public void productWithCategory() {
        productRepository.deleteById(1L);
    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte) 1);
    }

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("test");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }


    public void fetchProductsByCriteria() {
        var product = productRepository.findProductByCriteria("prod", BigDecimal.valueOf(1), null);
        product.forEach(System.out::println);
    }

    public void fetchSortedProduct() {
        Sort sort = Sort.by("name").and(
                Sort.by("price").descending()
        );

        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        products.forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();

        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Elements: " + totalElements);

    }

    public void fetchProductBySpecification(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }

        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(spec).forEach(System.out::println);

    }

    @Transactional
    public void fetchUsers() {
        var user = userRepository.findAllWithAddresses();
        user.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void fetchLoyaltyPoints() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach(p -> System.out.println(p.getId() + ": " + p.getEmail()));
    }
}
