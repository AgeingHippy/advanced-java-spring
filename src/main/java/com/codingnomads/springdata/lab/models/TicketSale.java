package com.codingnomads.springdata.lab.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ticket_sales",uniqueConstraints = @UniqueConstraint(name="ticket_sales_uk",columnNames = {"area_id","route_id"}))
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketSale {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Area area;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Route route;

    @Column(nullable = false)
    private int price;

}
