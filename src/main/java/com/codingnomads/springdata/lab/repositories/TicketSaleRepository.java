package com.codingnomads.springdata.lab.repositories;

import com.codingnomads.springdata.lab.models.Area;
import com.codingnomads.springdata.lab.models.Route;
import com.codingnomads.springdata.lab.models.TicketSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketSaleRepository extends JpaRepository<TicketSale, Long> {

    public List<TicketSale> findAllByArea_code(String areaCode);

    public List<TicketSale> findAllByRoute_code(String routeCode);

    public List<TicketSale> findAllByRoute(Route route);

    @Query("""
            SELECT ts
            FROM TicketSale ts
            JOIN Route r
            ON ts.route = ?1""")
    public List<TicketSale> findAllTicketSalesForRoute(Route route);

    @Query("""
            SELECT ts, r, a
            FROM TicketSale ts
            JOIN Route r
            ON ts.route = r
            JOIN Area a
            ON r.origin = a
            WHERE a = ?1""")
    public List<TicketSale> findAllTicketSalesForOrigin(Area origin);

}
