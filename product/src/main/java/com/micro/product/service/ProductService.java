package com.micro.product.service;

import com.micro.product.dto.PageResponse;
import com.micro.product.dto.ProductPaginatedResponse;
import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.model.*;
import com.micro.product.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final TaxRepository taxRepository;
    private final VendorRepository vendorRepository;
    @Autowired
    private HttpServletRequest request;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository, TaxRepository taxRepository, VendorRepository vendorRepository){
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.taxRepository = taxRepository;
        this.vendorRepository = vendorRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setSku(productRequest.getSku());
        product.setBarcode(productRequest.getBarcode());
        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setStatus(productRequest.getStatus());
        product.setWeight(productRequest.getWeight());

        Optional<Brand> optionalBrand = brandRepository.findById(productRequest.getBrand_id());
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            product.setBrand(brand);
        } else {
            throw new IllegalArgumentException("Brand not found with ID: " + productRequest.getBrand_id());
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productRequest.getCategory_id());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            product.setCategory(category);
        } else {
            throw new IllegalArgumentException("Category not found with ID: " + productRequest.getCategory_id());
        }
        Optional<Tax> optionalTax = taxRepository.findById(productRequest.getTax_id());
        if (optionalTax.isPresent()) {
            Tax tax = optionalTax.get();
            product.setTax(tax);
        } else {
            throw new IllegalArgumentException("Tax not found with ID: " + productRequest.getTax_id());
        }
        Optional<Vendor> optionalVendor = vendorRepository.findById(productRequest.getVendor_id());
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            product.setVendor(vendor);
        } else {
            throw new IllegalArgumentException("Vendor not found with ID: " + productRequest.getVendor_id());
        }
        productRepository.save(product);

//                .builder()
//            .name(productRequest.getName())
//            .description(productRequest.getDescription())
//            .price(productRequest.getPrice())
//            .build();

        List<Image> images = new ArrayList<>();

        try {
            if (productRequest.getImageFiles() != null && !productRequest.getImageFiles().isEmpty()) {
                for (MultipartFile imageFile : productRequest.getImageFiles()) {
                    String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                    Path path = Paths.get("/Users/anas/Desktop/java17_microservices/images/" + fileName);
                    Files.copy(imageFile.getInputStream(), path);

                    // Create an Image object and associate it with the product
                    Image image = new Image();
                    image.setProduct(product);
                    image.setName(path.toString());
//                                        .url(fileName)
//                                        .product(product)
//                                        .build();
                    images.add(image);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save image for product: "+product.getName()+e.toString());
        }

        // If you have a setter method for images in the Product class
        product.setImages(images);

        productRepository.save(product);
//        log.info("Product {} is saved", product.getId());
//        Product product = Product.builder()
//                .name(productRequest.getName())
//                .description(productRequest.getDescription())
//                .price(productRequest.getPrice())
//                .build();
//
//        productRepository.save(product);
//        log.info("Product {} is saved", product.getId());
    }
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }
    @Transactional(readOnly = true)
    public ProductPaginatedResponse getAllProductsPaginated(int page, int size, String sortBy, String sortOrder, String searchTerm) {
        // Create Pageable object to represent pagination information
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.fromString(sortOrder), sortBy);

        // Handle sorting
        Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortBy);

        // Fetch a page of products from the repository
        Page<Product> productPage;

        if (searchTerm != null && !searchTerm.isEmpty()) {
            // If searchTerm is provided, perform search
            productPage = productRepository.findByNameContainingIgnoreCase(searchTerm, pageable);
        } else {
            // If no searchTerm, just apply pagination and sorting
            productPage = productRepository.findAll(pageable);
        }
        List<ProductResponse> productResponses = productPage.map(this::mapToProductResponse).toList();
        PageResponse pageResponse = new PageResponse();
        pageResponse.setLength((int) productPage.getTotalElements());
        pageResponse.setSize(size);
        pageResponse.setPage(page);
        pageResponse.setLastPage(productPage.getTotalPages());
        pageResponse.setStartIndex((page - 1) * size + 1);
        pageResponse.setEndIndex((int) Math.min(page * size, productPage.getTotalElements()));

        ProductPaginatedResponse productPaginatedResponse = new ProductPaginatedResponse();
        productPaginatedResponse.setPagination(pageResponse);
        productPaginatedResponse.setProducts(productResponses);

        return productPaginatedResponse;
    }
    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setSku(product.getSku());
        productResponse.setStatus(product.getStatus());
        productResponse.setStock(product.getStock());
        productResponse.setBarcode(product.getBarcode());
        productResponse.setWeight(product.getWeight());
        productResponse.setBrand(product.getBrand().getId());
        productResponse.setBrandObject(product.getBrand());
        productResponse.setCategory(product.getCategory().getId());
        productResponse.setCategoryObject(product.getCategory());
        productResponse.setTaxObject(product.getTax());
        productResponse.setTax(product.getTax().getId());
        productResponse.setVendorObject(product.getVendor());
        productResponse.setVendor(product.getVendor().getId());
        Set<Long> tagIds = product.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());
        productResponse.setTags(tagIds);
//        productResponse.setTags((Set<Long>) product.getTags().stream().map(tag -> {
//            return tag.getId();
//        }));
        productResponse.setTagsObjects(product.getTags());

        // Construct the image URL
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        List<String> imageUrls = product.getImages().stream()
                .map(image -> "http://localhost:8080" + "/api/products/images/" + Paths.get(image.getName()).getFileName())
                .toList();
        productResponse.setImages(imageUrls);

        return productResponse;
    }
    public byte[] downloadImageFromFileSystem(String imageName) throws IOException {
        byte[] images = Files.readAllBytes(new File("/Users/anas/Desktop/java17_microservices/images/"+imageName).toPath());
        return images;
    }
}
