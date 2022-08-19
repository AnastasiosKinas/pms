package com.tasoskinas.Post.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //allilouxia tou user me post comment
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-comments")
    private User user;

    @Column(name = "text")
    private String text;

    //Many-to-One Relationship
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    @Transient
    private int postId;

    @Transient
    private String username;

    public int getPostId() {
        if (post != null) {return post.getId();}

        return 0;
    }

    public String getUsername() {
        if (user != null) {return user.getUsername();}

        return "";
    }
}
