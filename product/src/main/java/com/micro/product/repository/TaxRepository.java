package com.micro.product.repository;

import com.micro.product.model.Tax;
import org.springframework.data.repository.CrudRepository;

public interface TaxRepository extends CrudRepository<Tax, Integer> {
}
