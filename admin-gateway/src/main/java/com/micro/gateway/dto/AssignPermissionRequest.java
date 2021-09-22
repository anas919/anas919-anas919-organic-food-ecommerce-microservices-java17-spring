package com.micro.gateway.dto;

import java.util.List;

public class AssignPermissionRequest {
    private Integer role_id;
    private List<String> permissions;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
