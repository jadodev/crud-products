package com.jhonnathan.crud_products.domain.port.out;

import com.jhonnathan.crud_products.domain.entity.Product;

import java.util.List;

public interface ExportProductPort {
    byte[] generateExcel(List<Product> products);
}
