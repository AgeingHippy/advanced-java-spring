package com.codingnomads.springtest.lab.service;

import com.codingnomads.springtest.lab.SpringTestLab;
import com.codingnomads.springtest.lab.entity.Movie;
import com.codingnomads.springtest.lab.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = SpringTestLab.class)
class MovieServiceImplTest {

    Movie movie1;
    Movie movie2;
    Movie movie3;

    @MockBean
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    @BeforeEach
    void setUp() {
        movie1 = Movie.builder().id(1L).name("Movie 1").rating(5.5).build();
        movie2 = Movie.builder().id(2L).name("Movie Two").rating(7.5).build();
        movie3 = Movie.builder().id(3L).name("Third Movie").rating(3.5).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllMovies() {
        when(movieRepository.findAll()).thenReturn(List.of(movie1,movie2,movie3));

        List<Movie> movies = movieService.getAllMovies();

        assertThat(movies.size()).isEqualTo(3);
        assertThat(movies.get(0).getName()).isEqualTo("Movie 1");
        assertThat(movies.get(1).getName()).isEqualTo("Movie Two");
        assertThat(movies.get(2).getName()).isEqualTo("Third Movie");
    }

    @Test
    void getMoviesWithMinimumRating() {
        when(movieRepository.findByRatingGreaterThanEqual(5.5)).thenReturn(List.of(movie2,movie3));
        when(movieRepository.findByRatingGreaterThanEqual(9.9)).thenReturn(List.of());

        List<Movie> movies = movieService.getMoviesWithMinimumRating(5.5);

        assertThat(movies.size()).isEqualTo(2);
        assertThat(movies.get(0).getName()).isEqualTo("Movie Two");
        assertThat(movies.get(1).getName()).isEqualTo("Third Movie");

        movies = movieService.getMoviesWithMinimumRating(9.9);
        assertThat(movies.size()).isEqualTo(0);
    }
}