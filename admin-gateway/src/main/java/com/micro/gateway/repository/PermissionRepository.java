package com.micro.gateway.repository;

import com.micro.gateway.model.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {
    List<Permission> findByNameIn(List<String> names);

}
