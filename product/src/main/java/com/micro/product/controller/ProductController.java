package com.micro.product.controller;

import com.micro.product.dto.ProductFilterRequest;
import com.micro.product.dto.ProductPaginatedResponse;
import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //Admin Routes
    @PostMapping("admin/products/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@ModelAttribute ProductRequest productRequest, @RequestParam("imageFiles") MultipartFile[] files) {
        productService.createProduct(productRequest);
    }

    @GetMapping("admin/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("admin/products/paginated")
    @ResponseStatus(HttpStatus.OK)
    public ProductPaginatedResponse getProductsPaginated(@ModelAttribute ProductFilterRequest productFilterRequest) {
        return productService.getAllProductsPaginated(productFilterRequest.getPage(), productFilterRequest.getSize(), productFilterRequest.getSort(), productFilterRequest.getOrder(), productFilterRequest.getSearch());
    }

    //Visitor Routes

    @GetMapping("products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getVisitorProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("products/images/{imageName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String imageName) throws IOException {
        byte[] imageData = productService.downloadImageFromFileSystem(imageName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
