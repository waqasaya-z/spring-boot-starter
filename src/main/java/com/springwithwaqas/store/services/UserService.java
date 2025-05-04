package com.springwithwaqas.store.services;

import com.springwithwaqas.store.entities.Address;
import com.springwithwaqas.store.entities.Category;
import com.springwithwaqas.store.entities.Product;
import com.springwithwaqas.store.entities.User;
import com.springwithwaqas.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
}
