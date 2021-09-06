package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    Post findById(int theId);

    void save(Post thePost, String token);

    void deleteById(int theId);
}
