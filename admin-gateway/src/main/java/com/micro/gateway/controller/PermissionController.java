package com.micro.gateway.controller;

import com.micro.gateway.dto.CreatePermissionRequest;
import com.micro.gateway.dto.CreateRoleRequest;
import com.micro.gateway.model.Permission;
import com.micro.gateway.model.Role;
import com.micro.gateway.service.PermissionService;
import com.micro.gateway.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/permissions")
@RestController
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Permission> register(@RequestBody CreatePermissionRequest createPermissionRequest) {
        Permission createdPermission = permissionService.addPermission(createPermissionRequest);

        return ResponseEntity.ok(createdPermission);
    }
}
