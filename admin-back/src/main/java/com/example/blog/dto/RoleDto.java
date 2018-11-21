package com.example.blog.dto;

import java.util.List;

public class RoleDto {
    private String roleName;
    private List<Integer> ids;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
