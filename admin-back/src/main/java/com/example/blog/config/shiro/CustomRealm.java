package com.example.blog.config.shiro;

import com.example.blog.dao.UserDao;
import com.example.blog.domain.User;
import com.example.blog.domain.user.Permission;
import com.example.blog.domain.user.Role;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    private UserDao userDao;

    public CustomRealm(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) { //授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = userDao.getUserByName(userName);
        Role role = userDao.getRoleByUserId(user.getId());
        List<Permission> permissionList = userDao.getPermissionByRole(role.getId());
        authorizationInfo.addRole(role.getName());
        Set<String> perList=new HashSet<>();
        for (Permission permission : permissionList) {
            perList.add(permission.getPath());
        }
       authorizationInfo.setStringPermissions(perList);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException { //密码匹配
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        User user = userDao.getUserByAccount(userName, password);
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
