package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    private Genre genre;

    @Column(nullable = false)
    private double price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_platform",
            inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private Set<Platform> platforms;

    public void addPlatform(Platform platform) {
        if (platforms == null) {
            platforms = new HashSet<>();
        }
        platforms.add(platform);
    }

}
