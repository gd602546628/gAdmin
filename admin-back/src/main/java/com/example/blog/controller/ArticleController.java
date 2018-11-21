package com.example.blog.controller;

import com.example.blog.constant.CommonCode;
import com.example.blog.dao.ArticleMapper;
import com.example.blog.domain.Article;
import com.example.blog.responseUtil.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    ArticleMapper articleMapper;

    @PostMapping(value = "/add")
    public ResponseVo addArticle(@RequestBody Article article) {
        ResponseVo responseVo = new ResponseVo();
        article.setCreatetime(new Date());
        articleMapper.insert(article);
        responseVo.setCode(CommonCode.SUCCESS.getCode());
        responseVo.setMessage("添加文章成功");
        return responseVo;
    }
}
