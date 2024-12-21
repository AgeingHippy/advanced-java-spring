package com.codingnomads.springdata.example.ddl.manytomany.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String name;

    @ManyToMany( mappedBy = "interests")
    private Set<User> users;
}
