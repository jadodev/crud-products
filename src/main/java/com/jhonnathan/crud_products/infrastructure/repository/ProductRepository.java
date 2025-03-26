package com.jhonnathan.crud_products.infrastructure.repository;

import com.jhonnathan.crud_products.domain.entity.Product;
import com.jhonnathan.crud_products.domain.port.out.ProductRepositoryPort;
import com.jhonnathan.crud_products.exceptions.ProductNotFoundException;
import com.jhonnathan.crud_products.exceptions.StockException;
import com.jhonnathan.crud_products.infrastructure.entity.ProductEntity;
import com.jhonnathan.crud_products.infrastructure.mapper.ProductEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements ProductRepositoryPort {
    private final ProductRepositoryJpa repositoryJpa;

    public ProductRepository(ProductRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = ProductEntityMapper.toEntity(product);
        return ProductEntityMapper.toDomain(repositoryJpa.save(productEntity));
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repositoryJpa.findById(id).map(ProductEntityMapper::toDomain);
    }

    @Override
    public List<Product> getAllProducts() {
        return repositoryJpa.findAll().stream()
                .map(ProductEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Product update(Long id, Product product) {
        return repositoryJpa.findById(id)
                .map(p -> {
                    Product existingProduct = ProductEntityMapper.toDomain(p);

                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setCategory(product.getCategory());
                    existingProduct.setStock(product.getStock());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setImages(product.getImages());

                    ProductEntity updatedProductEntity = ProductEntityMapper.toEntity(existingProduct);
                    updatedProductEntity.setId(p.getId());

                    updatedProductEntity = repositoryJpa.save(updatedProductEntity);

                    return ProductEntityMapper.toDomain(updatedProductEntity);
                })
                .orElseThrow(() -> new ProductNotFoundException("producto no encontrado"));
    }

    @Override
    public void delete(Long id) {
        if (repositoryJpa.existsById(id)){
            repositoryJpa.deleteById(id);
        }else {
            throw new ProductNotFoundException("Producto no encontrado");
        }

    }

    @Override
    public Product updateStock(Long productId, int quantity) {
        return repositoryJpa.findById(productId)
                .map(p -> {
                    Product product = ProductEntityMapper.toDomain(p);

                    if (product.getStock() < quantity) {
                        throw new StockException("Stock insuficiente para el producto con ID: " + productId);
                    }

                    product.setStock(product.getStock() - quantity);
                    ProductEntity updatedProductEntity = ProductEntityMapper.toEntity(product);
                    updatedProductEntity.setId(p.getId());

                    updatedProductEntity = repositoryJpa.save(updatedProductEntity);
                    return ProductEntityMapper.toDomain(updatedProductEntity);
                })
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + productId));
    }
}





























