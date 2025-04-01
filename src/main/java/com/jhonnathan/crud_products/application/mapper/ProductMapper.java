package com.jhonnathan.crud_products.application.mapper;

import com.jhonnathan.crud_products.application.dto.ProductDTO;
import com.jhonnathan.crud_products.domain.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Product> toDomainList(List<ProductDTO> dtos){
        return  dtos.stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }
}