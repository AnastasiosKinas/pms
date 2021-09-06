package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.Post;
import com.tasoskinas.Post.Management.System.entity.User;
import com.tasoskinas.Post.Management.System.repository.PostRepository;
import com.tasoskinas.Post.Management.System.repository.UserRepository;
import com.tasoskinas.Post.Management.System.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository thePostRepository, JwtUtil jwtUtil, UserRepository userRepository) {
        postRepository = thePostRepository;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(int theId) {
        Optional<Post> result = postRepository.findById(theId);

        Post thePost;

        if (result.isPresent()) {
            thePost = result.get();
        } else {
            // we didn't find the post
            throw new RuntimeException("Did not find post id - " + theId);
        }

        return thePost;
    }

    @Override
    public void save(Post thePost, String token) {
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByUsername(username);

        thePost.setUser(user);

        postRepository.save(thePost);
    }

    @Override
    public void deleteById(int theId) {
        postRepository.deleteById(theId);
    }
}
