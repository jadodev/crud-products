package com.jhonnathan.crud_products.infrastructure.repository;

import com.jhonnathan.crud_products.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, Long> {
}
