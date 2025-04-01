package com.jhonnathan.crud_products.infrastructure.controller;

import com.jhonnathan.crud_products.application.service.ExcelReportService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ExcelController {
    private final ExcelReportService excelService;

    public ExcelController(ExcelReportService excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportProducts() {
        byte[] excelData = excelService.generateExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename("products.xlsx").build());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelData);
    }
}
