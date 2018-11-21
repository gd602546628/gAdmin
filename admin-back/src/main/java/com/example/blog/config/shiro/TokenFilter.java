package com.example.blog.config.shiro;

import com.alibaba.fastjson.JSON;
import com.example.blog.constant.CommonCode;
import com.example.blog.dao.UserDao;
import com.example.blog.domain.User;
import com.example.blog.responseUtil.ResponseVo;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenFilter extends AccessControlFilter {
    private UserDao userDao;

    public TokenFilter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("token");
        User user = userDao.getUserByToken(token);
        if (token == null || token.equals("") || user == null) {

            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(CommonCode.DISABLETOKEN.getCode());
        responseVo.setMessage(CommonCode.DISABLETOKEN.getmessage());
        writer.write(JSON.toJSONString(responseVo));
        return false;
    }
}
