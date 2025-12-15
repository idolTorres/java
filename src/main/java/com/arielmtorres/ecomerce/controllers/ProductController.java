package com.arielmtorres.ecomerce.controllers;

import com.arielmtorres.ecomerce.dtos.ProductCreateDTO;
import com.arielmtorres.ecomerce.dtos.ProductDTO;
import com.arielmtorres.ecomerce.dtos.ProductUpdateDTO;
import com.arielmtorres.ecomerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // para pruebas
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET /api/products?name=...&price=...
    @GetMapping
    public List<ProductDTO> list(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) Double price) {
        return service.list(name, price);
    }

    // GET /api/products/<built-in function id>
    @GetMapping("/<built-in function id>")
    public ProductDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductCreateDTO dto) {
        ProductDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/products/<built-in function id>
    @PutMapping("/<built-in function id>")
    public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO dto) {
        return service.update(id, dto);
    }

    // DELETE /api/products/<built-in function id>
    @DeleteMapping("/<built-in function id>")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
