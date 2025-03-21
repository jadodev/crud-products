package com.jhonnathan.crud_products.domain.port.in;

import com.jhonnathan.crud_products.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServicePort {
    Product create(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product update(Long id, Product product);
    void delete(Long id);
}
