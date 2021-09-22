package com.micro.gateway.service;

import com.micro.gateway.dto.CreatePermissionRequest;
import com.micro.gateway.dto.CreateRoleRequest;
import com.micro.gateway.model.Permission;
import com.micro.gateway.model.Role;
import com.micro.gateway.repository.PermissionRepository;
import com.micro.gateway.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission addPermission(CreatePermissionRequest input) {
        Permission permission = new Permission();
        permission.setName(input.getName());

        return permissionRepository.save(permission);
    }
}
