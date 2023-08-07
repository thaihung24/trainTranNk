package com.example.hungnt.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "users", schema = "testdata", catalog = "")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "email")
    @Email(message = "Email không hợp lệ")
    private String email;
    @Basic
    @Column(name = "phone")
    @NotEmpty(message = "Thiếu phone")
    private String phone;
    @Basic
    @Column(name = "address")
    @NotEmpty(message = "Thiếu address")
    private String address;

    public UsersEntity() {
    }

    public UsersEntity(String email, String phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
