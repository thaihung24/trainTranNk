package com.example.hungnt.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "posts", schema = "testdata", catalog = "")
public class PostsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "userId")
    @NotEmpty(message = "Thieu UserId")
    private long userId;
    @Basic
    @Column(name = "content")
    @NotEmpty(message = "Thieu Content")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostsEntity that = (PostsEntity) o;
        return id == that.id && userId == that.userId && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, content);
    }
}
