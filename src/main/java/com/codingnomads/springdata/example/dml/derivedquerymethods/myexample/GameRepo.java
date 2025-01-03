package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game,Long> {

    List<Game> findByName(String name);

    List<Game> findByNameContaining(String pattern);

    List<Game> findByPrice(double price);

    List<Game> findByPriceGreaterThan(double price);

    List<Game> findByPriceGreaterThanEqual(double price);

    List<Game> findByPriceLessThan(double price);

    List<Game> findByPriceLessThanEqual(double price);

    List<Game> findByPriceGreaterThanEqualAndPriceLessThanEqual(double lower, double upper);
    List<Game> findByPriceGreaterThanAndPriceLessThan(double lower, double upper);
    List<Game> findByPriceBetween(double lower, double upper);

    List<Game> findByPlatforms_name(String platformName);

    List<Game> findByPlatforms_name(String platformName, Pageable pageable);

    List<Game> findByPlatforms(Platform platform);

    List<Game> findByPlatforms(Platform platform, Pageable pageable);

    List<Game> findByGenreIsNot(Genre genre);

}
