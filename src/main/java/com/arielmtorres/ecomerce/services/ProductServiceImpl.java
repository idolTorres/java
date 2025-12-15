package com.arielmtorres.ecomerce.services;

import com.arielmtorres.ecomerce.dtos.ProductCreateDTO;
import com.arielmtorres.ecomerce.dtos.ProductDTO;
import com.arielmtorres.ecomerce.dtos.ProductUpdateDTO;
import com.arielmtorres.ecomerce.exceptions.ProductNotFoundException;
import com.arielmtorres.ecomerce.models.Product;
import com.arielmtorres.ecomerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public ProductDTO create(ProductCreateDTO dto) {
        Product p = new Product(
                null,
                dto.getName(),
                dto.getPrice(),
                dto.getDescription(),
                dto.getCategory()
        );
        Product saved = repo.save(p);
        return toDTO(saved);
    }

    @Override
    public List<ProductDTO> list(String name, Double price) {
        List<Product> products;

        boolean hasName = name != null && !name.isBlank();
        boolean hasPrice = price != null;

        if (hasName && hasPrice) {
            products = repo.findByNameContainsAndPrice(name, price);
        } else if (hasName) {
            products = repo.findByNameContains(name);
        } else if (hasPrice) {
            products = repo.findByPrice(price);
        } else {
            products = repo.findAll();
        }

        return products.stream().map(this::toDTO).toList();
    }

    @Override
    public ProductDTO getById(Long id) {
        Product p = repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return toDTO(p);
    }

    @Override
    public ProductDTO update(Long id, ProductUpdateDTO dto) {
        Product existing = repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        existing.setDescription(dto.getDescription());
        existing.setCategory(dto.getCategory());

        Product saved = repo.save(existing);
        return toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        repo.deleteById(id);
    }

    private ProductDTO toDTO(Product p) {
        return new ProductDTO(p.getId(), p.getName(), p.getPrice(), p.getDescription(), p.getCategory());
    }
}
