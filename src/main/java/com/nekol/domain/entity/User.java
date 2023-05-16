package com.nekol.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.websocket.OnError;
import java.util.Set;

@Setter
@Getter
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    private String fullName;
    private boolean isLoggedIn;

    @OneToMany(mappedBy = "user")
    private Set<Category> categories;

    @OneToMany(mappedBy = "user")
    private Set<Wallet> wallets;



}
