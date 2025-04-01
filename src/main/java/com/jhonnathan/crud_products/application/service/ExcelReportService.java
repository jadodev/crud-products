package com.jhonnathan.crud_products.application.service;

import com.jhonnathan.crud_products.application.dto.ProductDTO;
import com.jhonnathan.crud_products.application.mapper.ProductMapper;
import com.jhonnathan.crud_products.domain.entity.Product;
import com.jhonnathan.crud_products.domain.port.out.ExportProductPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelReportService {
    private final ExportProductPort exportProduct;
    private final ProductApplicationService productService;

    public ExcelReportService(ExportProductPort exportProduct,ProductApplicationService productService ) {
        this.exportProduct = exportProduct;
        this.productService = productService;
    }

    public byte[] generateExcel() {
        List<ProductDTO> productDTO = productService.getAll();
        List<Product> productList = ProductMapper.toDomainList(productDTO);
        return  exportProduct.generateExcel(productList);
    }
}
