package com.codingnomads.springdata.example.ddl.manytoone.unidirectional.usingonetomany;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(unique = true, nullable = false)
    private String Name;

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    private List<Post> posts;
}
