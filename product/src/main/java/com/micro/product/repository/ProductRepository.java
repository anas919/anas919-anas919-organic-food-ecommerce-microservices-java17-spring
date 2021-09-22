package com.micro.product.repository;

import com.micro.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Page<Product> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
}
