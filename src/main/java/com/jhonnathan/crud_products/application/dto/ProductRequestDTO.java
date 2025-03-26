package com.jhonnathan.crud_products.application.dto;

public class ProductRequestDTO {
    private long id;
    private int quantity;

    public ProductRequestDTO() {}

    public ProductRequestDTO(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
