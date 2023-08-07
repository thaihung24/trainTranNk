package com.example.hungnt.utils;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ResponseLogin {
    private HttpStatus statusCode;
    private String message;
    private ResponseLoginData data;

    public ResponseLoginData getData() {
        return data;
    }

    public void setData(ResponseLoginData data) {
        this.data = data;
    }

    public ResponseLogin(HttpStatus statusCode, String message, ResponseLoginData data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ResponseLogin() {
    }



    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
