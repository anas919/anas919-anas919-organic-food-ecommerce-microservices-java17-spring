package com.micro.product.controller;

import com.micro.product.dto.BrandRequest;
import com.micro.product.dto.BrandResponse;
import com.micro.product.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBrand(@ModelAttribute BrandRequest brandRequest) {
        brandService.createBrand(brandRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponse> getBrands() {
        return brandService.getAllBrands();
    }

}
