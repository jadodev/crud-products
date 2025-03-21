package com.jhonnathan.crud_products.application.service;

import com.jhonnathan.crud_products.application.dto.ProductDTO;
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
    private final CachePort cachePort;

    public ProductApplicationService(ProductDomainService service, CloudinaryImagePort uploadImage, CachePort cachePort) {
        this.service = service;
        this.uploadImage = uploadImage;
        this.cachePort = cachePort;
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
        ProductDTO createdProductDTO = ProductMapper.toDto(createdProduct);

        cachePort.guardarEnCache("product_" + createdProduct.getId(), createdProductDTO, 10, TimeUnit.MINUTES);
        return createdProductDTO;
    }

    public Optional<ProductDTO> getById(long id) {
        ProductDTO cachedProduct = (ProductDTO) cachePort.obtenerDeCache("product_" + id);
        if (cachedProduct != null) {
            return Optional.of(cachedProduct);
        }
        Optional<ProductDTO> productDTO = service.getProductById(id).map(ProductMapper::toDto);
        productDTO.ifPresent(p -> cachePort.guardarEnCache("product_" + id, p, 10, TimeUnit.MINUTES));

        return productDTO;
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
        ProductDTO updatedProductDTO = ProductMapper.toDto(updatedProduct);

        cachePort.eliminarDeCache("product_" + id);

        cachePort.guardarEnCache("product_" + id, updatedProductDTO, 10, TimeUnit.MINUTES);

        return updatedProductDTO;
    }


    public void delete(long id) {
        Optional<Product> productId = service.getProductById(id);

        if (!productId.isPresent()) {
            throw new ProductNotFoundException("Producto no encontrado");
        } else {
            service.delete(id);
            cachePort.eliminarDeCache("product_" + id); // Eliminar de caché
        }
    }
}
