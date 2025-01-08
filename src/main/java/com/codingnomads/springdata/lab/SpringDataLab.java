/* CodingNomads (C)2024 */
package com.codingnomads.springdata.lab;

import com.codingnomads.springdata.lab.models.Area;
import com.codingnomads.springdata.lab.models.Route;
import com.codingnomads.springdata.lab.models.TicketSale;
import com.codingnomads.springdata.lab.repositories.AreaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codingnomads.springdata.lab.repositories.RouteRepository;
import com.codingnomads.springdata.lab.repositories.TicketSaleRepository;
import com.codingnomads.springdata.lab.services.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataLab implements CommandLineRunner {

    private final AreaRepository areaRepository;
    private final RouteRepository routeRepository;
    private final TicketSaleRepository ticketSaleRepository;
    private final MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataLab.class);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Route> routes = new ArrayList<>();
        List<TicketSale> ticketSales = new ArrayList<>();
        Route route;

        if (areaRepository.count() == 0) {
            final List<Area> areas = areaRepository.saveAll(Arrays.asList(
                    Area.builder().code("G").build(),
                    Area.builder().code("H").build(),
                    Area.builder().code("Y").build(),
                    Area.builder().code("Z").build()));

            areaRepository.saveAll(Arrays.asList(
                    Area.builder().code("M").build(),
                    Area.builder().code("N").build(),
                    Area.builder().code("O").build(),
                    Area.builder().code("P").build()
            ));

            routes.add(Route.builder().origin(areaRepository.findByCode("G"))
                    .destination(areaRepository.findByCode("H")).build());
            routes.add(Route.builder().origin(areaRepository.findByCode("H"))
                    .destination(areaRepository.findByCode("Y")).build());
            routes.add(Route.builder().origin(areaRepository.findByCode("Y"))
                    .destination(areaRepository.findByCode("Z")).build());
            routes.add(Route.builder().origin(areaRepository.findByCode("Z"))
                    .destination(areaRepository.findByCode("G")).build());
            routes.add(Route.builder().origin(areaRepository.findByCode("M"))
                    .destination(areaRepository.findByCode("N")).build());
            routes.add(Route.builder().origin(areaRepository.findByCode("N"))
                    .destination(areaRepository.findByCode("M")).build());
            routes.add(Route.builder().origin(areaRepository.findByCode("O"))
                    .destination(areaRepository.findByCode("P")).build());

            routes.add(Route.builder().origin(areaRepository.findByCode("H"))
                    .destination(areaRepository.findByCode("P")).build());
            routes.add(Route.builder().origin(areaRepository.findByCode("P"))
                    .destination(areaRepository.findByCode("H")).build());

            routeRepository.saveAll(routes);

            //add ticketSaleLocations
            ticketSales.add(TicketSale.builder()
                    .area(areaRepository.findByCode("H"))
                    .route(routeRepository.findByCode("G-H"))
                    .build());
            ticketSales.add(TicketSale.builder()
                    .area(areaRepository.findByCode("H"))
                    .route(routeRepository.findByCode("H-Y"))
                    .build());
            ticketSales.add(TicketSale.builder()
                    .area(areaRepository.findByCode("Y"))
                    .route(routeRepository.findByCode("H-Y"))
                    .build());
            ticketSales.add(TicketSale.builder()
                    .area(areaRepository.findByCode("Y"))
                    .route(routeRepository.findByCode("Y-Z"))
                    .build());

            ticketSales.add(TicketSale.builder()
                    .area(areaRepository.findByCode("H"))
                    .route(routeRepository.findByCode("H-P"))
                    .build());
            ticketSales.add(TicketSale.builder()
                    .area(areaRepository.findByCode("P"))
                    .route(routeRepository.findByCode("H-P"))
                    .build());


            ticketSaleRepository.saveAll(ticketSales);
        }

        myService.fetchAndPrintAllRoutsWithArea("Y");

        route = myService.findRouteByCode("O-P");
        System.out.println("== Route details for route code: " + "O-P" + " ==");
        System.out.println(route);

        myService.printTickedOfficesSellingRoutsWithOrigin(areaRepository.findByCode("H"));

    }
}
