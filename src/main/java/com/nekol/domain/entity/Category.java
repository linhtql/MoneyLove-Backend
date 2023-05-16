package com.nekol.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;
    private String icon;
    private String color;
//    @Column(nullable = false)
//    private int typeCategory;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryParent", referencedColumnName = "id")
    private Category categoryParent;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "categoryParent")
    private List<Category> categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;

}
