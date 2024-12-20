package com.codingnomads.springdata.example.ddl.onetoone.bidirectional;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "helmets")
@Data
public class Helmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String pattern;

    @OneToOne(mappedBy = "helmet")
    private Driver driver;

}
