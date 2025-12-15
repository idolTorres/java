package com.arielmtorres.ecomerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductCreateDTO {
    @NotBlank(message = "name es obligatorio")
    private String name;

    @NotNull(message = "price es obligatorio")
    @Positive(message = "price debe ser mayor a 0")
    private Double price;

    @NotBlank(message = "description es obligatorio")
    private String description;

    @NotBlank(message = "category es obligatorio")
    private String category;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
