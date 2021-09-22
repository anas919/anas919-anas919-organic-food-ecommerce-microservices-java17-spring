package com.micro.product.controller;


import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.dto.VendorRequest;
import com.micro.product.dto.VendorResponse;
import com.micro.product.service.ProductService;
import com.micro.product.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/products/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createVendor(@ModelAttribute VendorRequest vendorRequest) {
        vendorService.createVendor(vendorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VendorResponse> getVendors() {
        return vendorService.getAllVendors();
    }
}
