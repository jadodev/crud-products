package com.jhonnathan.crud_products.infrastructure.entity;

import com.jhonnathan.crud_products.domain.state.Avaible;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "category")
    private List<String> category;

    @Enumerated(EnumType.STRING)
    private Avaible avaible;

    private Long stock;
    private Double price;
    private Double totalPrice;
    private Double discount;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;

    private LocalDateTime createAt;

    public ProductEntity() {}

    public ProductEntity(
            String name,
            String description,
            List<String> category,
            Avaible avaible,
            Long stock,
            double price,
            double totalPrice,
            Double discount,
            List<String> images,
            LocalDateTime createAt
    ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.avaible = avaible;
        this.stock = stock;
        this.price = price;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.images = images;
        this.createAt = createAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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
}
