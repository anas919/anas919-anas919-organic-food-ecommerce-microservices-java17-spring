package com.micro.gateway.controller;

import com.micro.gateway.dto.AssignPermissionRequest;
import com.micro.gateway.dto.CreateRoleRequest;
import com.micro.gateway.dto.RegisterUserRequest;
import com.micro.gateway.model.Role;
import com.micro.gateway.model.User;
import com.micro.gateway.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/roles")
@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) { this.roleService = roleService; }

    @PostMapping("/add")
    public ResponseEntity<Role> register(@RequestBody CreateRoleRequest createRoleRequest) {
        Role createdRole = roleService.addRole(createRoleRequest);

        return ResponseEntity.ok(createdRole);
    }
    @PostMapping("/assign-permissions")
    public ResponseEntity<Role> assignPermissions(@RequestBody AssignPermissionRequest input) {
        Role updatedRole = roleService.assignPermissions(input);
        return ResponseEntity.ok(updatedRole);
    }


}
