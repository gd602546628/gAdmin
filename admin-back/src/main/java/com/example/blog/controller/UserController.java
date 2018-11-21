package com.example.blog.controller;

import com.example.blog.constant.CommonCode;
import com.example.blog.dao.UserDao;
import com.example.blog.domain.User;
import com.example.blog.domain.user.Permission;
import com.example.blog.dto.RoleDto;
import com.example.blog.exceptionHandler.CommonException;
import com.example.blog.responseUtil.ResponseVo;
import com.example.blog.service.UserService;
import com.example.blog.util.ValidatorUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequiresPermissions("user")
    @PostMapping(value = "/public/test")
    public String test() {
        return "ssssws";
    }

    @PostMapping(value = "/public/login")
    public User login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        subject.login(token);
        User user1 = userService.login(user);
        return user1;
    }

    @PostMapping(value = "/public/insertPermission")
    public ResponseVo insertPermission(@RequestBody Permission permission) {
        ResponseVo responseVo = new ResponseVo();
        userService.insertPermission(permission);
        responseVo.setMessage(CommonCode.SUCCESS.getmessage());
        responseVo.setCode(CommonCode.SUCCESS.getCode());
        return responseVo;
    }

    @PostMapping(value = "/public/insertRole")
    public ResponseVo insertRole(@RequestBody RoleDto roleDto) {
        userService.insertRole(roleDto.getRoleName(), roleDto.getIds());
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMessage(CommonCode.SUCCESS.getmessage());
        responseVo.setCode(CommonCode.SUCCESS.getCode());
        return responseVo;
    }
}
