package com.nekol.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
public class Category extends Base{

    @Column(nullable = false, unique = true)
    private String name;
    private String icon;
    private String color;

}
