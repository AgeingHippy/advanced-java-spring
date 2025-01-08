package com.codingnomads.springdata.lab.repositories;

import com.codingnomads.springdata.lab.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {

    public List<Route> findByOrigin_codeOrDestination_code(String originCode, String destinationCode );

    public Route findByCode(String routeCode);
}
