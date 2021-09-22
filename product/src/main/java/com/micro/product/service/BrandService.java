package com.micro.product.service;

import com.micro.product.dto.BrandRequest;
import com.micro.product.dto.BrandResponse;
import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.model.Brand;
import com.micro.product.model.Product;
import com.micro.product.repository.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    public void createBrand(BrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setName(brandRequest.getName());

        brandRepository.save(brand);
    }

    @Transactional(readOnly = true)
    public List<BrandResponse> getAllBrands() {
        List<Brand> brands = (List<Brand>) brandRepository.findAll();
        return brands.stream().map(brand -> mapToBrandResponse(brand)).toList();
    }

    private BrandResponse mapToBrandResponse(Brand brand) {
        BrandResponse brandResponse = new BrandResponse();
        brandResponse.setId(brand.getId());
        brandResponse.setName(brand.getName());
        return brandResponse;
    }
}
