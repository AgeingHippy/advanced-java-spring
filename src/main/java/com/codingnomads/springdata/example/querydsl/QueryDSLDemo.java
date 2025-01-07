/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.querydsl;

import com.codingnomads.springdata.example.querydsl.models.*;
import com.codingnomads.springdata.example.querydsl.repository.AreaRepository;
import com.codingnomads.springdata.example.querydsl.repository.RouteRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
// @EnableJpaRepositories("com.codingnomads.springdata.example.querydsl.repository")
public class QueryDSLDemo implements CommandLineRunner {

    private final RouteRepository routeRepository;
    private final AreaRepository areaRepository;
    private final EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(QueryDSLDemo.class);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        final List<Area> areas = areaRepository.saveAll(Arrays.asList(
                Area.builder().code("A").name("A").build(),
                Area.builder().code("B").name("B").build(),
                Area.builder().code("C").name("C").build(),
                Area.builder().code("D").name("D").build(),
                Area.builder().code("E").name("E").build(),
                Area.builder().code("F").name("F").build(),
                Area.builder().code("G").name("G").build(),
                Area.builder().code("H").name("H").build(),
                Area.builder().code("I").name("I").build()));

        final List<Route> routes = routeRepository.saveAll(Arrays.asList(
                Route.builder()
                        .code("A-B")
                        .name("A-B")
                        .origin(areaRepository.findByCode("A"))
                        .destination(areaRepository.findByCode("B"))
                        .build(),
                Route.builder()
                        .code("B-C")
                        .name("B-C")
                        .origin(areaRepository.findByCode("B"))
                        .destination(areaRepository.findByCode("C"))
                        .build(),
                Route.builder()
                        .code("C-D")
                        .name("C-D")
                        .origin(areaRepository.findByCode("C"))
                        .destination(areaRepository.findByCode("D"))
                        .build(),
                Route.builder()
                        .code("D-E")
                        .name("D-E")
                        .origin(areaRepository.findByCode("D"))
                        .destination(areaRepository.findByCode("E"))
                        .build(),
                Route.builder()
                        .code("E-F")
                        .name("E-F")
                        .origin(areaRepository.findByCode("E"))
                        .destination(areaRepository.findByCode("F"))
                        .build(),
                Route.builder()
                        .code("F-G")
                        .name("F-G")
                        .origin(areaRepository.findByCode("F"))
                        .destination(areaRepository.findByCode("G"))
                        .build()));

        final List<Route> routesByCode = routeRepository.findAllRoutesBySearchQuery(
                SearchQuery.builder().code("A-B").build());
        routesByCode.forEach(System.out::println);

        final List<Route> routesByCodeAndOrigin = routeRepository.findAllRoutesBySearchQuery(
                SearchQuery.builder().code("A-B").origin("A").build());

        routesByCodeAndOrigin.forEach(System.out::println);

        // query the database straight-up without using repository
        QArea qArea = QArea.area;
        JPAQuery<?> query = new JPAQuery<>(entityManager);
        Area area = query.select(qArea).from(qArea).where(qArea.code.eq("A")).fetchOne();
        System.out.println(area);

        System.out.println("== Any route using area 'B' ==");
        QRoute qRoute = QRoute.route;
        query = new JPAQuery<>(entityManager);
        List<Route> routes2 =
                query.select(qRoute)
                        .from(qRoute)
                        .where(
                                qRoute.origin.code.eq("B")
                                        .or(qRoute.destination.code.eq("B"))
                        )
                        .fetch();
        routes2.forEach(System.out::println);

        System.out.println("== Only areas A,C,E and G");
        query = new JPAQuery<>(entityManager);
        List<Area> areas2 = query.select(qArea)
                .from(qArea)
                .where(qArea.code.in("A", "C", "E", "G"))
                .fetch();
        areas2.forEach(System.out::println);

        System.out.println("== unused areas (left join) ==");
        query = new JPAQuery<>(entityManager);
        List<Area> areas3 = query.select(qArea)
                .from(qArea)
                .leftJoin(qRoute).on(qRoute.origin.eq(qArea).or(qRoute.destination.eq(qArea)))
                .where(qRoute.id.isNull())
                .fetch();
        areas3.forEach(System.out::println);

        routeRepository.deleteAll();
        areaRepository.deleteAll();
    }
}
