package com.jhonnathan.crud_products.infrastructure.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jhonnathan.crud_products.domain.port.out.CloudinaryImagePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CloudinaryAdapter implements CloudinaryImagePort {

    private final Cloudinary cloudinary;

    public CloudinaryAdapter(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}") String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret
    ) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    @Override
    public List<String> uploadImages(List<MultipartFile> images) {
        return images.stream().map(image -> {
            try {
                Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                return (String) uploadResult.get("secure_url");
            } catch (IOException e) {
                throw new RuntimeException("Error subiendo imagen a Cloudinary", e);
            }
        }).collect(Collectors.toList());
    }
}
