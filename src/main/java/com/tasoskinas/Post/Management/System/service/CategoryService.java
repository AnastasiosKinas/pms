package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(int theId);

    void save(Category thePost, String token);

    void deleteById(int theId);
}
