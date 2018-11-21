package com.example.blog.dao;

import com.example.blog.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CategoryDao {
    public List<Category> getList();

    public void add(String name);

    public Category getByName(String name);
}
