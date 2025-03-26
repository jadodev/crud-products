package com.jhonnathan.crud_products.application.service;

import com.jhonnathan.crud_products.application.dto.ProductDTO;
import com.jhonnathan.crud_products.application.dto.ProductRequestDTO;
import com.jhonnathan.crud_products.application.dto.ProductResponseDTO;
import com.jhonnathan.crud_products.application.mapper.ProductMapper;
import com.jhonnathan.crud_products.domain.entity.Product;
import com.jhonnathan.crud_products.domain.port.out.CachePort;
import com.jhonnathan.crud_products.domain.port.out.CloudinaryImagePort;
import com.jhonnathan.crud_products.domain.service.ProductDomainService;
import com.jhonnathan.crud_products.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProductApplicationService {
    private final ProductDomainService service;
    private final CloudinaryImagePort uploadImage;

    public ProductApplicationService(ProductDomainService service, CloudinaryImagePort uploadImage) {
        this.service = service;
        this.uploadImage = uploadImage;
    }

    public ProductDTO create(ProductDTO productDTO, List<MultipartFile> images) throws IOException {
        Product product = ProductMapper.toDomain(productDTO);
        if (images != null && !images.isEmpty()) {
            try {
                List<String> imageUrls = uploadImage.uploadImages(images);
                product.setImages(imageUrls);
            } catch (RuntimeException e) {
                throw new IOException("Error al subir imágenes a Cloudinary", e);
            }
        }

        Product createdProduct = service.create(product);
        return ProductMapper.toDto(createdProduct);
    }

    public Optional<ProductDTO> getById(long id) {
        return service.getProductById(id).map(ProductMapper::toDto);
    }

    public List<ProductDTO> getAll(){
        List<Product> products = service.getAllProducts();
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDTO update(long id, ProductDTO productDTO, List<MultipartFile> images) throws IOException {
        Product product = service.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));

        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());

        if (images != null && !images.isEmpty()) {
            List<String> imagesUrls = uploadImage.uploadImages(images);
            product.setImages(imagesUrls);
        }

        Product updatedProduct = service.update(id, product);
        return ProductMapper.toDto(updatedProduct);
    }

    public void delete(long id) {
        Optional<Product> productId = service.getProductById(id);
        if (!productId.isPresent()) {
            throw new ProductNotFoundException("Producto no encontrado");
        } else {
            service.delete(id);
        }
    }

    public List<ProductResponseDTO> requestProducts(List<ProductRequestDTO> productRequests) {
        return productRequests.stream().map(request -> {
            Product product = service.getProductById(request.getId())
                    .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));

            if (product.getStock() < request.getQuantity()) {
                return new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getCategory(),
                        product.getAvaible(),
                        product.getStock(),
                        product.getPrice(),
                        product.getTotal_price(),
                        product.getDiscount(),
                        product.getImages()
                );
            }
            product.setStock(product.getStock() - request.getQuantity());
            Product updatedProduct = service.update(product.getId(), product);

            return new ProductResponseDTO(
                    updatedProduct.getId(),
                    updatedProduct.getName(),
                    updatedProduct.getDescription(),
                    updatedProduct.getCategory(),
                    updatedProduct.getAvaible(),
                    updatedProduct.getStock(),
                    updatedProduct.getPrice(),
                    updatedProduct.getTotal_price(),
                    updatedProduct.getDiscount(),
                    updatedProduct.getImages()
            );
        }).collect(Collectors.toList());
    }
}




































