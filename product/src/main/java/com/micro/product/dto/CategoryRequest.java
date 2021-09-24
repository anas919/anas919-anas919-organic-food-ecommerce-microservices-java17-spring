package com.micro.product.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CategoryRequest {
    private String name;
    MultipartFile imageFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }


}
