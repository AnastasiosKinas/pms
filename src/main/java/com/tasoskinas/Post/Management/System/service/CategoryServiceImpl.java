package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.Category;
import com.tasoskinas.Post.Management.System.entity.User;
import com.tasoskinas.Post.Management.System.repository.CategoryRepository;
import com.tasoskinas.Post.Management.System.repository.UserRepository;
import com.tasoskinas.Post.Management.System.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository theCategoryRepository,
                               JwtUtil jwtUtil,
                               UserRepository userRepository) {
        this.categoryRepository = theCategoryRepository;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int theId) {
        Optional<Category> result = categoryRepository.findById(theId);

        Category theCategory;

        if (result.isPresent()) {
            theCategory = result.get();
        } else {
            // we didn't find the  category
            throw new RuntimeException("Did not find  category id - " + theId);
        }

        return theCategory;
    }

    @Override
    public void save(Category theCategory, String token) {
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByUsername(username);

        theCategory.setUser(user);

        categoryRepository.save(theCategory);
    }

    @Override
    public void deleteById(int theId) {
        categoryRepository.deleteById(theId);
    }
}
