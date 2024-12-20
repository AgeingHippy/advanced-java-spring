package com.codingnomads.springdata.example.ddl.manytoone.unidirectional.usingmanytoone;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(unique = true, nullable = false)
    private String Name;
}
