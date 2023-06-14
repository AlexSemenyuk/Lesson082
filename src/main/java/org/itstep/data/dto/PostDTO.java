package org.itstep.data.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class PostDTO {
    private String title;
    private LocalDateTime published;
    private String image;
    private String content;

    public PostDTO(String title, LocalDateTime published, String image, String content) {
        this.title = title;
        this.published = LocalDateTime.now();
        this.image = image;
        this.content = content;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "title='" + title + '\'' +
                ", published=" + published +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
