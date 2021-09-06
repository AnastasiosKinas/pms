package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int theId);

    void save(User theUser);

    void deleteById(int theId);

}
