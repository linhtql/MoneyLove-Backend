package com.nekol.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User extends Base {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    private String fullName;

}
