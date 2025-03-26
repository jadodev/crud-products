package com.jhonnathan.crud_products.domain.port.out;

import com.jhonnathan.crud_products.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product update(Long id, Product product);
    void delete(Long id);
    Product updateStock(Long id, int quantity);
}
