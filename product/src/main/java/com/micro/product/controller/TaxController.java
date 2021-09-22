package com.micro.product.controller;


import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.dto.TaxRequest;
import com.micro.product.dto.TaxResponse;
import com.micro.product.service.ProductService;
import com.micro.product.service.TaxService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/products/taxes")
public class TaxController {

    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTax(@ModelAttribute TaxRequest taxRequest) {
        taxService.createTax(taxRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaxResponse> getTaxes() {
        return taxService.getAllTaxes();
    }
}
