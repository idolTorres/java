package com.arielmtorres.ecomerce.services;

import com.arielmtorres.ecomerce.dtos.ProductCreateDTO;
import com.arielmtorres.ecomerce.dtos.ProductDTO;
import com.arielmtorres.ecomerce.dtos.ProductUpdateDTO;

import java.util.List;

public interface ProductService {
    ProductDTO create(ProductCreateDTO dto);
    List<ProductDTO> list(String name, Double price);
    ProductDTO getById(Long id);
    ProductDTO update(Long id, ProductUpdateDTO dto);
    void delete(Long id);
}
