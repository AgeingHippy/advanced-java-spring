/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.lifecyclecallback;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PrintEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String value;

    // write your methods here
    @PrePersist
    public void myPrePersist() {
        System.out.println("PrintEntity PrePersist " + this.toString() );
    }

    @PreUpdate
    public void myPreUpdate() {
        System.out.println("PrintEntity PreUpdate " + this.toString());
    }

    @PreRemove
    public void myPreRemove() {
        System.out.println("PrintEntity PreRemove " + this.toString());
    }

    @PostPersist
    public void myPostPersist() {
        System.out.println("PrintEntity PostPersist " + this.toString());
    }

    @PostUpdate
    public void myPostUpdate() {
        System.out.println("PrintEntity PostUpdate " + this.toString());
    }

    @PostRemove
    public void myPostRemove() {
        System.out.println("PrintEntity PostRemove " + this.toString());
    }

    @PostLoad
    public void myPostLoad() {
        System.out.println("PrintEntity PostLoad " + this.toString());
    }


}
