package com.tasoskinas.Post.Management.System.repository;

import com.tasoskinas.Post.Management.System.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment,Integer>{
}
