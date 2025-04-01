package com.jhonnathan.crud_products.application.dto;

import com.jhonnathan.crud_products.domain.state.Avaible;

import java.util.List;

public class ProductResponseDTO {
    private long id;
    private String name;
    private String description;
    private List<String> category;
    private Avaible avaible;
    private long stock;
    private double price;
    private List<String> images;

    public ProductResponseDTO(
            long id,
            String name,
            String description,
            List<String> category,
            Avaible avaible,
            long stock,
            double price,
            List<String> images) {
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

    public void setId(long id) {
        this.id = id;
    }

}
