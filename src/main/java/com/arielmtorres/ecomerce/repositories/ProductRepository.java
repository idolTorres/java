package com.arielmtorres.ecomerce.repositories;

import com.arielmtorres.ecomerce.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product p);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);

    List<Product> findByNameContains(String name);
    List<Product> findByPrice(Double price);
    List<Product> findByNameContainsAndPrice(String name, Double price);
}
