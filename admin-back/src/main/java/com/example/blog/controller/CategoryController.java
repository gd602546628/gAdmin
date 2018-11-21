package com.example.blog.controller;

import com.example.blog.constant.CommonCode;
import com.example.blog.dao.CategoryDao;
import com.example.blog.domain.Category;
import com.example.blog.responseUtil.ResponseListVo;
import com.example.blog.responseUtil.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

    @PostMapping(value = "/add")
    public ResponseVo addCategory(@RequestBody Map<String, String> params) {
        ResponseVo responseVo = new ResponseVo();
        Category category = categoryDao.getByName(params.get("name"));
        if (category != null) {
            responseVo.setMessage("该分类已存在");
            responseVo.setCode(CommonCode.PARAMERROR.getCode());
            return responseVo;
        }
        categoryDao.add(params.get("name"));
        responseVo.setMessage("添加分类成功");
        return responseVo;
    }

    @PostMapping(value = "/public/getList")
    public ResponseListVo getList(@RequestBody Map<String, String> params) {
        ResponseListVo<Category> responseListVo = new ResponseListVo<>();
        System.out.println(params.get("pageNum"));
        PageHelper.startPage(Integer.parseInt(params.get("pageNum")), Integer.parseInt(params.get("pageSize")));
        List<Category> categoryList = categoryDao.getList();
        PageInfo<Category> categoryPageInfo = new PageInfo<>(categoryList);
        responseListVo.setData(categoryPageInfo);
        responseListVo.setMessage("查询分类成功");
        responseListVo.setCode("000000");
        return responseListVo;
    }
}
