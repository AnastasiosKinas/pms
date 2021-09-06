package com.tasoskinas.Post.Management.System.rest;

import com.tasoskinas.Post.Management.System.entity.User;
import com.tasoskinas.Post.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll().stream().peek(u -> u.setPassword(null)).collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User theUser = userService.findById(userId);

        if (theUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }

        theUser.setPassword(null);

        return theUser;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theUser.setId(0);

        userService.save(theUser);

        theUser.setPassword(null);

        return theUser;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {
        userService.save(theUser);

        theUser.setPassword(null);

        return theUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        User tempUser = userService.findById(userId);

        // throw exception if null
        if (tempUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }

        userService.deleteById(userId);

        return "Deleted user id - " + userId;
    }
}
