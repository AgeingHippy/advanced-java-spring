package com.codingnomads.springdata.example.ddl.manytomany.unidirectional;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true,nullable = false)
    private String name;
}
