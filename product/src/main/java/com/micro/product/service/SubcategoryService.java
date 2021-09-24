package com.micro.product.service;

import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.SubcategoryRequest;
import com.micro.product.model.Category;
import com.micro.product.model.Product;
import com.micro.product.model.Subcategory;
import com.micro.product.repository.CategoryRepository;
import com.micro.product.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubcategoryService(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }


    public void createSubcategory(SubcategoryRequest subcategoryRequest) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryRequest.getName());

        Optional<Category> optionalCategory = categoryRepository.findById(subcategoryRequest.getCategory_id());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            subcategory.setCategory(category);
        } else {
            throw new IllegalArgumentException("Category not found with ID: " + subcategoryRequest.getCategory_id());
        }
        subcategoryRepository.save(subcategory);
    }

}
