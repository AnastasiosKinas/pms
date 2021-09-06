package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment findById(int theId);

    void save(Comment theComment, String token);

    void deleteById(int theId);
}
