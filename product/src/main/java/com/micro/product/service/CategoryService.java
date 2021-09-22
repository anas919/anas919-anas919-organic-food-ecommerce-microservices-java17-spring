package com.micro.product.service;

import com.micro.product.dto.BrandRequest;
import com.micro.product.dto.BrandResponse;
import com.micro.product.dto.CategoryRequest;
import com.micro.product.dto.CategoryResponse;
import com.micro.product.model.Brand;
import com.micro.product.model.Category;
import com.micro.product.repository.BrandRepository;
import com.micro.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public void createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());

        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        return categories.stream().map(category -> mapToCategoryResponse(category)).toList();
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
}
