package com.example.hungnt.utils;

import com.example.hungnt.models.PostsEntity;
import org.springframework.http.HttpStatus;

public class ResponsePost {
    private HttpStatus status;
    private String message;
    private PostsEntity data;

    public ResponsePost() {
    }

    public ResponsePost(HttpStatus status, String message, PostsEntity data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PostsEntity getData() {
        return data;
    }

    public void setData(PostsEntity data) {
        this.data = data;
    }
}
