package com.tasoskinas.Post.Management.System.repository;

import com.tasoskinas.Post.Management.System.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post,Integer>{
}
