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
    private double total_price;
    private Double discount;
    private List<String> images;
    private LocalDateTime createAt;

    public ProductDTO() {};

    public ProductDTO(
            long id,
            String name,
            String description,
            List<String> category,
            Avaible avaible,
            long stock,
            double price,
            double total_price,
            Double discount,
            List<String> images,
            LocalDateTime createAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.avaible = avaible;
        this.stock = stock;
        this.price = price;
        this.total_price = total_price;
        this.discount = discount != null ? discount : 0.0;
        this.images = images;
        this.createAt = createAt;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}

