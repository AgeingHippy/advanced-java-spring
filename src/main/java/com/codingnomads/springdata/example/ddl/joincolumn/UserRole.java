package com.codingnomads.springdata.example.ddl.joincolumn;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table( uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","role_id"}))
@Data
public class UserRole {
//    @EmbeddedId
//    private UserRolePrimaryKey userRolePrimaryKey;
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Role role;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;
}

//@Embeddable
//@Data
//class UserRolePrimaryKey implements Serializable {
//    private User user;
//    private Role role;
//
//}
