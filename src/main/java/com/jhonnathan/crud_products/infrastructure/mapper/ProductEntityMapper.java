package com.jhonnathan.crud_products.infrastructure.mapper;

import com.jhonnathan.crud_products.domain.entity.Product;
import com.jhonnathan.crud_products.infrastructure.entity.ProductEntity;

public class ProductEntityMapper {
    public static ProductEntity toEntity(Product product){
        return new ProductEntity(
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getAvaible(),
                product.getStock(),
                product.getPrice(),
                product.getTotal_price(),
                product.getDiscount(),
                product.getImages(),
                product.getCreateAt()
        );
    }

    public static Product toDomain(ProductEntity productEntity){
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getCategory(),
                productEntity.getAvaible(),
                productEntity.getStock(),
                productEntity.getPrice(),
                productEntity.getTotalPrice(),
                productEntity.getDiscount(),
                productEntity.getImages(),
                productEntity.getCreateAt()
        );
    }
}
