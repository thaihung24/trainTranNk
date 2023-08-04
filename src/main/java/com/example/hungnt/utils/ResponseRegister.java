package com.example.hungnt.utils;

import com.example.hungnt.models.CustomersEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class ResponseRegister {
    private HttpStatus statusCode;
    private String message;
    private Optional<CustomersEntity> data;

    public Optional<CustomersEntity> getData() {
        return data;
    }

    public void setData(Optional<CustomersEntity> data) {
        this.data = data;
    }

    public ResponseRegister(HttpStatus statusCode, String message, Optional<CustomersEntity> data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ResponseRegister() {
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
