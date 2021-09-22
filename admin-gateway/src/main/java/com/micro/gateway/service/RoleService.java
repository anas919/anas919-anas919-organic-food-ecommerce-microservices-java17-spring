package com.micro.gateway.service;

import com.micro.gateway.dto.AssignPermissionRequest;
import com.micro.gateway.dto.CreateRoleRequest;
import com.micro.gateway.dto.RegisterUserRequest;
import com.micro.gateway.model.Permission;
import com.micro.gateway.model.Role;
import com.micro.gateway.model.User;
import com.micro.gateway.repository.PermissionRepository;
import com.micro.gateway.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public Role addRole(CreateRoleRequest input) {
        Role role = new Role();
        role.setName(input.getName());

        return roleRepository.save(role);
    }
    public Role assignPermissions(AssignPermissionRequest request) {
        Optional<Role> optionalRole = roleRepository.findById(request.getRole_id());
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            List<String> permissionNames = request.getPermissions();
            List<Permission> permissions = permissionRepository.findByNameIn(permissionNames);
            role.setPermissions(new HashSet<>(permissions));
            return roleRepository.save(role);
        } else {
            throw new IllegalArgumentException("Role not found with ID: " + request.getRole_id());
        }
    }
}
