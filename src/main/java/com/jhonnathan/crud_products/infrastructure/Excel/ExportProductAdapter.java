package com.jhonnathan.crud_products.infrastructure.Excel;

import com.jhonnathan.crud_products.domain.entity.Product;
import com.jhonnathan.crud_products.domain.port.out.ExportProductPort;
import com.jhonnathan.crud_products.exceptions.ExcelError;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExportProductAdapter implements ExportProductPort {
    @Override
    public byte[] generateExcel(List<Product> products) {
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Products");
            Row haeaderRow = sheet.createRow(0);

            String[] columns = {"ID", "Name", "Description", "Category", "Available", "Stock", "Price"};
            for (int i = 0; i < columns.length; i++){
                Cell cell = haeaderRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowIdx = 1;
            for(Product product: products){
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getDescription());
                row.createCell(3).setCellValue(", " + product.getCategory());
                row.createCell(4).setCellValue(product.getAvaible().toString());
                row.createCell(5).setCellValue(product.getStock());
                row.createCell(6).setCellValue(product.getPrice());
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new ExcelError("Error al generar el archivo de excel");
        }
    }
}
