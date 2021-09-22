package com.micro.product.dto;

import com.micro.product.model.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
public class ProductResponse {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
    private BigDecimal weight;
    private Boolean status;
    private Brand brandObject;
    private Long brand;
    private Category categoryObject;
    private Long category;
    private Vendor vendorObject;
    private Long vendor;
    private Tax taxObject;
    private Long tax;
    private Set<Tag> tagsObjects;
    private Set<Long> tags;

    public Set<Tag> getTagsObjects() {
        return tagsObjects;
    }

    public void setTagsObjects(Set<Tag> tagsObjects) {
        this.tagsObjects = tagsObjects;
    }

    public void setTags(Set<Long> tags) {
        this.tags = tags;
    }


    public Long getTax() {
        return tax;
    }


    public Tax getTaxObject() {
        return taxObject;
    }

    public void setTaxObject(Tax taxObject) {
        this.taxObject = taxObject;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public void setCategoryObject(Category categoryObject) {
        this.categoryObject = categoryObject;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getVendor() {
        return vendor;
    }

    public void setVendor(Long vendor) {
        this.vendor = vendor;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public Vendor getVendorObject() {
        return vendorObject;
    }

    public void setVendorObject(Vendor vendorObject) {
        this.vendorObject = vendorObject;
    }

    public Category getCategoryObject() {
        return categoryObject;
    }

    public void setCategory(Category categoryObject) {
        this.categoryObject = categoryObject;
    }


    public Brand getBrandObject() {
        return brandObject;
    }

    public void setBrandObject(Brand brandObject) {
        this.brandObject = brandObject;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    private String barcode;
    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public ProductResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
