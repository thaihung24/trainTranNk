package com.example.hungnt.models;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;


@Entity
@Table(name = "customers", schema = "testdata", catalog = "")
public class CustomersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "email")
    @Email(message = "Email không hợp lệ")
    private String email;
    @Basic
    @Column(name = "name")
    @NotEmpty(message = "Thiếu name")
    private String name;
    @Basic
    @Column(name = "password")

    private String password;
    @Basic
    @Column(name = "address")
    @NotEmpty(message ="Thiếu address" )
    private String address;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void hasPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        setPassword(passwordEncoder.encode(password));
    }
    public boolean comparePassword(String enterPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return   passwordEncoder.matches(enterPassword,this.password);
    }
    public String generateJWT(){
        return Jwts.builder()
                .claim("customerId",getId())
                .setExpiration(new Date(System.currentTimeMillis() + 900000))
                .signWith(SignatureAlgorithm.HS256,"JoinQuit123@")
                .compact();
    }
}
