package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.Comment;
import com.tasoskinas.Post.Management.System.entity.User;
import com.tasoskinas.Post.Management.System.repository.CommentRepository;
import com.tasoskinas.Post.Management.System.repository.UserRepository;
import com.tasoskinas.Post.Management.System.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository theCommentRepository, JwtUtil jwtUtil, UserRepository userRepository) {
        this.commentRepository = theCommentRepository;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(int theId) {
        Optional<Comment> result = commentRepository.findById(theId);

        Comment theComment;

        if (result.isPresent()) {
            theComment = result.get();
        } else {
            // we didn't find the comment
            throw new RuntimeException("Did not find comment id - " + theId);
        }

        return theComment;
    }

    @Override
    public void save(Comment theComment, String token) {
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByUsername(username);

        theComment.setUser(user);

        commentRepository.save(theComment);
    }
    @Override
    public void deleteById(int theId) {
        commentRepository.deleteById(theId);
    }
}
