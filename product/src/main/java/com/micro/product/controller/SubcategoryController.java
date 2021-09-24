package com.micro.product.controller;

import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.SubcategoryRequest;
import com.micro.product.service.ProductService;
import com.micro.product.service.SubcategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }
    //Admin Routes
    @PostMapping("admin/products/subcategories/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@ModelAttribute SubcategoryRequest subcategoryRequest, @RequestParam("imageFile") MultipartFile file) {
        subcategoryService.createSubcategory(subcategoryRequest);
    }
}
