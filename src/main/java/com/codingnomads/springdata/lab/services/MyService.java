package com.codingnomads.springdata.lab.services;

import com.codingnomads.springdata.lab.models.Area;
import com.codingnomads.springdata.lab.models.Route;
import com.codingnomads.springdata.lab.models.TicketSale;
import com.codingnomads.springdata.lab.repositories.RouteRepository;
import com.codingnomads.springdata.lab.repositories.TicketSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyService {
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    TicketSaleRepository ticketSaleRepository;

    @Transactional(readOnly = true)
    public void fetchAndPrintAllRoutsWithArea(String areaCode) {
        List<Route> routes = routeRepository.findByOrigin_codeOrDestination_code(areaCode,areaCode);
        routes.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public Route findRouteByCode(String code) {
        Route route = routeRepository.findByCode("O-P");
        //Access destination and origin to avoid lazyload issues later
        route.getOrigin().getCode();
        route.getDestination().getCode();
        return route;
    }

    @Transactional(readOnly = true)
    public void printTickedOfficesSellingRoutsWithOrigin(Area origin) {
        System.out.println("== Ticket sales for routes with origin " + origin.getCode() + " ==");
        List<TicketSale> ticketSales = ticketSaleRepository.findAllTicketSalesForOrigin(origin);
        ticketSales.forEach(System.out::println);
    }

}
