package com.micro.product.service;

import com.micro.product.dto.CategoryRequest;
import com.micro.product.dto.CategoryResponse;
import com.micro.product.model.Category;
import com.micro.product.repository.CategoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    private HttpServletRequest request;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public void createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());

        categoryRepository.save(category);

        try {
            if (categoryRequest.getImageFile() != null && !categoryRequest.getImageFile().isEmpty()) {
                MultipartFile imageFile  = categoryRequest.getImageFile();
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                Path path = Paths.get("/Users/anas/Documents/vegist/organic-food-ecommerce-microservices-java17-spring/images/" + fileName);
                Files.copy(imageFile.getInputStream(), path);

                category.setImage(path.toString());


            }else{
                System.out.println("No ImageFile Parameter");

            }
        } catch (IOException e) {
            System.out.println("Failed to save image for category: "+category.getName()+e.toString());
        }


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

        // Construct the image URL
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        String imageUrl = category.getImage();
        //        List<String> imageUrls = category.getImage().stream()
//                .map(image -> "http://localhost:8080" + "/api/products/images/" + Paths.get(image.getName()).getFileName())
//                ;
        categoryResponse.setImage("http://localhost:8080" + "/api/products/images/" + Paths.get(imageUrl).getFileName());


        return categoryResponse;
    }
}
