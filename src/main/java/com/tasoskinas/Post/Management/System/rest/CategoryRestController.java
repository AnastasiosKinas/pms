package com.tasoskinas.Post.Management.System.rest;

import com.tasoskinas.Post.Management.System.entity.Category;
import com.tasoskinas.Post.Management.System.service.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService theCategoryService) {
        categoryService = theCategoryService;
    }

    // expose "/categories" and return list of Categories
    @GetMapping("/categories")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    // add mapping for GET /categories/{categoryId}

    @GetMapping("/categories/{categoryId}")
    public Category getCategory(@PathVariable int categoryId) {

        Category theCategory = categoryService.findById(categoryId);

        if (theCategory == null) {
            throw new RuntimeException("Category id not found - " + categoryId);
        }

        return theCategory;
    }

    // add mapping for Post /categories - add new category
    @PostMapping("/categories")
    public Category addaCategory(@RequestBody Category theCategory,
                                 @RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);

        // also, just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theCategory.setId(0);

        theCategory.setUser(null);

        categoryService.save(theCategory, token);

        return theCategory;
    }

    // add mapping for PUT /categories - update existing category

    @PutMapping("/categories")
    public Category updateCategory(@RequestBody Category theCategory) {
        theCategory.setUser(null);

        categoryService.save(theCategory, "");

        return theCategory;
    }

    // add mapping for DELETE /categories/{categoryId} - delete category

    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable int categoryId) {

        Category tempCategory = categoryService.findById(categoryId);

        // throw exception if null

        if (tempCategory == null) {
            throw new RuntimeException("Category id not found - " + categoryId);
        }

        categoryService.deleteById(categoryId);

        return "Deleted category id - " + categoryId;
    }
}
