package com.micro.gateway.dto;

public class CreateRoleRequest {
    private String name;
    private Integer role_id;

    public String getName() {
        return name;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
