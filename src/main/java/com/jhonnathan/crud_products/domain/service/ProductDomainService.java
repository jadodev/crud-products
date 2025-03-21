package com.jhonnathan.crud_products.domain.service;

import com.jhonnathan.crud_products.domain.entity.Product;
import com.jhonnathan.crud_products.domain.port.in.ProductServicePort;
import com.jhonnathan.crud_products.domain.port.out.ProductRepositoryPort;
import com.jhonnathan.crud_products.domain.state.Avaible;
import com.jhonnathan.crud_products.exceptions.DiscountException;
import com.jhonnathan.crud_products.exceptions.PriceException;
import com.jhonnathan.crud_products.exceptions.StockException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDomainService implements ProductServicePort {
    private  final ProductRepositoryPort repository;

    public ProductDomainService( ProductRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {

        if (product.getStock() <= 0){
            throw new StockException("El producto no puede ser menor o igual a cero");
        }

        if (product.getPrice() <= 0){
            throw new PriceException("El precio no puede ser menor o igual a cero");
        }

        if (product.getAvaible().equals("NoDisponible")){
            throw new StockException("El producto no puede iniciar no estando disponible");
        }
        Product p = repository.save(product);
        return p;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repository.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    @Override
    public Product update(Long id, Product product) {
        if (product.getStock() == 0){
            product.setAvaible(Avaible.NoDisponible);
        }

        if (product.getDiscount() < 0){
            throw new DiscountException("El descuento no puede ser menor a cero");
        }

        return repository.update(id, product);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
