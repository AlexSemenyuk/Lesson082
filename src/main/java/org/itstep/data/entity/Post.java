package org.itstep.data.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false, name = "title")
    private String title;
    @Column(length = 255, nullable = false, name = "published")
    private LocalDateTime published;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 255, nullable = false, name = "image_path")
    private String imagePath;
    @Column(columnDefinition = "text", nullable = false, name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "draft_id")
    private Draft draft;

    public Post() {
    }

    public Post(String title, LocalDateTime published, String imagePath, String content) {
        this.title = title;
        this.published = published;
        this.imagePath = imagePath;
        this.content = content;
    }

    public Post(String title, LocalDateTime published, User user, String imagePath, String content) {
        this.title = title;
        this.published = published;
        this.user = user;
        this.imagePath = imagePath;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", published=" + published +
                ", user=" + user +
                ", imagePath='" + imagePath + '\'' +
                ", content='" + content + '\'' +
                ", draft=" + draft +
                '}';
    }
}
