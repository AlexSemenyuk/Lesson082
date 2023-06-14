package org.itstep.data.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "draft")
public class Draft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, name = "mean")
    private Boolean mean;
    @OneToMany (mappedBy = "draft", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();
    public Draft() {
    }

    public Draft(Boolean mean) {
        this.mean = mean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getMean() {
        return mean;
    }

    public void setMean(Boolean mean) {
        this.mean = mean;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Draft{" +
                "id=" + id +
                ", mean='" + mean + '\'' +
                '}';
    }
    public void addPost(Post post) {
        System.out.println("addPost in Draft");
        if (posts.stream().noneMatch(p -> Objects.equals(p, post))) {
            posts.add(post);
        }
    }
}

