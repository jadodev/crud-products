package com.jhonnathan.crud_products.domain.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CloudinaryImagePort {
    List<String> uploadImages(List<MultipartFile> images) throws IOException;
}
