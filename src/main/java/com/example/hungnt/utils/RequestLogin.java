package com.example.hungnt.utils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RequestLogin {
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotEmpty(message = "Mật khẩu không được bỏ trống")
    private String password;




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RequestLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
