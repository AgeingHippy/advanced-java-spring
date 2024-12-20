package com.codingnomads.springdata.example.ddl.onetoone.unidirectional;

import jakarta.persistence.*;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    private Driver driver;
}
