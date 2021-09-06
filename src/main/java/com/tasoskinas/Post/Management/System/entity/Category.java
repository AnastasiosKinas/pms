package com.tasoskinas.Post.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-categories")
    private User user;

    @Column(name = "title", unique = true)
    private String title;

    @OneToMany(targetEntity = Post.class, mappedBy = "category", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();

    @Transient
    private String username;

    public String getUsername() {
        if (user != null) {return user.getUsername();}

        return "";
    }
}
