package com.example.blog.dao;

import com.example.blog.domain.User;
import com.example.blog.domain.user.Permission;
import com.example.blog.domain.user.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    public User getUserByName(String name);

    public User getUserById(int id);

    public User getUserByToken(String token);

    public User getUserByAccount(String name, String password);

    public void setToken(String token, int id);

    public void insertPermission(Permission permission);

    public void insertRole(Role role);

    public void insertRolePermission(@Param("roleId") int roleId, @Param("ids") List<Integer> ids);

    public Role getRoleByUserId(int userId);

    public List<Permission> getPermissionByRole(int roleId);
}
