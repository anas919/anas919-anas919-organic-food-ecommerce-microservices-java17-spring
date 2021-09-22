package com.micro.product.controller;


import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.dto.TagRequest;
import com.micro.product.dto.TagResponse;
import com.micro.product.service.ProductService;
import com.micro.product.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/products/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTag(@ModelAttribute TagRequest tagRequest) {
        tagService.createTag(tagRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponse> getTags() {
        return tagService.getAllTags();
    }
}
