package com.micro.product.dto;

import java.util.List;

public class ProductPaginatedResponse {

    private PageResponse pagination;
    private List<ProductResponse> products;

    public PageResponse getPagination() {
        return pagination;
    }

    public void setPagination(PageResponse pagination) {
        this.pagination = pagination;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
