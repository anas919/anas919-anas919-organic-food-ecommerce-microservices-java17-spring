package com.micro.product.repository;

import com.micro.product.model.Product;
import com.micro.product.model.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Integer> {
    Page<Subcategory> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
    Page<Subcategory> findAll(Pageable pageable);
}
