package com.example.hungnt.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Entity
@Table(name = "posts", schema = "testdata", catalog = "")
public class PostsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
//    @Basic
//    @Column(name = "userid")
//
//    private long userId;
    @ManyToOne
    @JoinColumn(name="userid",referencedColumnName = "id")
    private CustomersEntity customer;

    @Basic
    @Column(name = "content")
    @NotEmpty
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public CustomersEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomersEntity customer) {
        this.customer = customer;
    }

}
