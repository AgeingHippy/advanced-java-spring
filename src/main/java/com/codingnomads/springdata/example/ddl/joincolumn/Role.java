package com.codingnomads.springdata.example.ddl.joincolumn;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "privileges_by_role",
            joinColumns = @JoinColumn(name = "the_role_id"),
            inverseJoinColumns = @JoinColumn(name = "the_permission_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"the_role_id", "the_permission_id"}))
    private Set<Permission> permissions;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;
}
