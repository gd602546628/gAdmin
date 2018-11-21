package com.example.blog.service;

import com.example.blog.dao.UserDao;
import com.example.blog.domain.User;
import com.example.blog.domain.user.Permission;
import com.example.blog.domain.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User login(User user) {
        User user1 = userDao.getUserByAccount(user.getName(), user.getPassword());
        if (user1 != null) {
            Role role = userDao.getRoleByUserId(user1.getId());
            List<Permission> permissions = userDao.getPermissionByRole(role.getId());
            user1.setRoleId(role.getId());
            user1.setRoleName(role.getName());
            user1.setPermissionList(permissions);
            String token = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            user1.setToken(token);
            userDao.setToken(token, user1.getId());
        }
        return user1;
    }

    public void insertPermission(Permission permission) {
        userDao.insertPermission(permission);
    }

    @Transactional
    public void insertRole(String name, List<Integer> ids) {
        Role role = new Role();
        role.setName(name);
        userDao.insertRole(role);
        int roleId = role.getId();
        userDao.insertRolePermission(roleId, ids);
    }
}
