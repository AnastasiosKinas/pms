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
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-posts")
    private User user;

    @Column(name = "bigText", length = 3000)
    private String bigText;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @OneToMany(targetEntity = Comment.class, mappedBy = "post", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    @Transient
    private int categoryId;

    @Transient
    private String username;

    public int getCategoryId() {
        if (category != null) {return category.getId();}

        return 0;
    }

    public String getUsername() {
        if (user != null) {return user.getUsername();}

        return "";
    }
}
//  spring boot data jpa show only id of child entity on json
