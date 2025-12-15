package com.arielmtorres.ecomerce.repositories;

import com.arielmtorres.ecomerce.models.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
@Profile("local") // por defecto, usamos el perfil local (memoria)
public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> db = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);

    public InMemoryProductRepository() {
        seed();
    }

    private void seed() {
        save(new Product(null, "Guitarra Fender Player Stratocaster", 699.99, "Guitarra eléctrica versátil con pastillas single-coil.", "Cuerdas"));
        save(new Product(null, "Batería Pearl Export", 899.99, "Set completo de batería para estudio y vivo.", "Percusión"));
        save(new Product(null, "Teclado Yamaha PSR-E373", 199.99, "Teclado con 622 voces y funciones de aprendizaje.", "Teclados"));
        save(new Product(null, "Micrófono Shure SM58", 99.99, "Micrófono dinámico estándar para voz en vivo.", "Audio"));
        save(new Product(null, "Pedal Boss DS-1 Distortion", 99.99, "Pedal de distorsión clásico para guitarra eléctrica.", "Efectos"));
    }

    @Override
    public Product save(Product p) {
        if (p.getId() == null) {
            p.setId(seq.getAndIncrement());
            db.add(p);
            return p;
        }
        // update
        deleteById(p.getId());
        db.add(p);
        db.sort(Comparator.comparing(Product::getId));
        return p;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(db);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return db.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteById(Long id) {
        db.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return db.stream().anyMatch(p -> p.getId().equals(id));
    }

    @Override
    public List<Product> findByNameContains(String name) {
        String n = name == null ? "" : name.toLowerCase();
        return db.stream()
                .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(n))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByPrice(Double price) {
        return db.stream()
                .filter(p -> p.getPrice() != null && p.getPrice().equals(price))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameContainsAndPrice(String name, Double price) {
        String n = name == null ? "" : name.toLowerCase();
        return db.stream()
                .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(n))
                .filter(p -> p.getPrice() != null && p.getPrice().equals(price))
                .collect(Collectors.toList());
    }
}
