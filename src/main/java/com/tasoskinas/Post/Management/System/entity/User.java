package com.tasoskinas.Post.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tasoskinas.Post.Management.System.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(targetEntity = Category.class, mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user-categories")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(targetEntity = Post.class, mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user-posts")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(targetEntity = Comment.class, mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user-comments")
    private List<Comment> comments = new ArrayList<>();

}
