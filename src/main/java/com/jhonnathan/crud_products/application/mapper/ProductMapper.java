package com.jhonnathan.crud_products.application.mapper;

import com.jhonnathan.crud_products.application.dto.ProductDTO;
import com.jhonnathan.crud_products.domain.entity.Product;

public class ProductMapper {
    public static ProductDTO toDto (Product product){
        return  new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getCategory(),
            product.getAvaible(),
            product.getStock(),
            product.getPrice(),
            product.getImages()
        );
    }

    public static Product toDomain(ProductDTO productDTO){
        return  new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getCategory(),
                productDTO.getAvaible(),
                productDTO.getStock(),
                productDTO.getPrice(),
                productDTO.getImages()
        );
    }
}