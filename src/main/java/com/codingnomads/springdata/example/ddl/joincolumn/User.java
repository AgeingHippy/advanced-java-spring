/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.ddl.joincolumn;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany( mappedBy = "user")
    private Set<UserRole> userRoles;

}
