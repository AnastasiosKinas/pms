package com.tasoskinas.Post.Management.System.repository;


import com.tasoskinas.Post.Management.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
