package com.tasoskinas.Post.Management.System.rest;

import com.tasoskinas.Post.Management.System.entity.Comment;
import com.tasoskinas.Post.Management.System.service.CommentService;
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
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService theCommentService) {
        commentService = theCommentService;
    }

    // expose "/comments" and return list of Comments
    @GetMapping("/comments")
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    // add mapping for GET /comments/{commentId}

    @GetMapping("/comments/{commentId}")
    public Comment getComment(@PathVariable int commentId) {

        Comment theComment = commentService.findById(commentId);

        if (theComment == null) {
            throw new RuntimeException("Comment id not found - " + commentId);
        }

        return theComment;
    }

    // add mapping for Post /comments - add new comment
    @PostMapping("/comments")
    public Comment addComment(@RequestBody Comment theComment, @RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theComment.setId(0);

        theComment.setUser(null);

        commentService.save(theComment, token);

        return theComment;
    }

    // add mapping for PUT /comments - update existing comment

    @PutMapping("/comments")
    public Comment updateComment(@RequestBody Comment theComment) {
        theComment.setUser(null);

        commentService.save(theComment, "");

        return theComment;
    }

    // add mapping for DELETE /comments/{commentId} - delete comment

    @DeleteMapping("/comments/{commentId}")
    public String deleteComment(@PathVariable int commentId) {

        Comment tempComment = commentService.findById(commentId);

        // throw exception if null

        if (tempComment == null) {
            throw new RuntimeException("Comment id not found - " + commentId);
        }

        commentService.deleteById(commentId);

        return "Deleted comment id - " + commentId;
    }
}
