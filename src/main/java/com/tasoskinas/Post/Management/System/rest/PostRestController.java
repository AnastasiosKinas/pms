package com.tasoskinas.Post.Management.System.rest;

import com.tasoskinas.Post.Management.System.entity.Post;
import com.tasoskinas.Post.Management.System.service.PostService;
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
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService thePostService) {
        postService = thePostService;
    }

    // expose "/posts" and return list of posts
    @GetMapping("/posts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    // add mapping for GET /posts/{postId}

    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable int postId) {

        Post thePost = postService.findById(postId);

        if (thePost == null) {
            throw new RuntimeException("Post id not found - " + postId);
        }

        return thePost;
    }

    // add mapping for POST /posts - add new post
    @PostMapping("/posts")
    public Post addPost(@RequestBody Post thePost, @RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        thePost.setId(0);

        thePost.setUser(null);

        postService.save(thePost, token);

        return thePost;
    }

    // add mapping for PUT /posts - update existing post

    @PutMapping("/posts")
    public Post updatePost(@RequestBody Post thePost) {
        thePost.setUser(null);

        postService.save(thePost, "");

        return thePost;
    }

    // add mapping for DELETE /posts/{postId} - delete post

    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable int postId) {

        Post tempPost = postService.findById(postId);

        // throw exception if null

        if (tempPost == null) {
            throw new RuntimeException("Post id not found - " + postId);
        }

        postService.deleteById(postId);

        return "Deleted post id - " + postId;
    }
}
