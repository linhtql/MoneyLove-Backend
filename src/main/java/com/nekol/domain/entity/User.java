package com.nekol.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User extends Base {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    private String fullName;
    private boolean isLoggedIn;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Category> categories;



}
