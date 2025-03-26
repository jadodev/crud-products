package com.jhonnathan.crud_products.application.dto;

import com.jhonnathan.crud_products.domain.state.Avaible;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private List<String> category;
    private Avaible avaible;
    private long stock;
    private double price;
    private List<String> images;

    public ProductDTO() {};

    public ProductDTO(
            long id,
            String name,
            String description,
            List<String> category,
            Avaible avaible,
            long stock,
            double price,
            List<String> images
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.avaible = avaible;
        this.stock = stock;
        this.price = price;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Avaible getAvaible() {
        return avaible;
    }

    public void setAvaible(Avaible avaible) {
        this.avaible = avaible;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

