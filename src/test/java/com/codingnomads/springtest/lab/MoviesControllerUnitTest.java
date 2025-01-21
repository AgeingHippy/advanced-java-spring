package com.codingnomads.springtest.lab;

import com.codingnomads.springtest.lab.entity.Movie;
import com.codingnomads.springtest.lab.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//@WebMvcTest(MovieController.class)
//@ContextConfiguration(classes = SpringTestLab.class)
@SpringBootTest(classes = SpringTestLab.class)
@AutoConfigureMockMvc
public class MoviesControllerUnitTest {

    @MockBean
    MovieService movieService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllMoviesSuccessMockService() throws Exception {
        List<Movie> movies = List.of(
                Movie.builder().id(1L).name("Movie one").build(),
                Movie.builder().id(1L).name("Movie two").build(),
                Movie.builder().id(1L).name("Movie three").build()
        );

        when(movieService.getAllMovies()).thenReturn(movies);

        mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("Movie one"))
                .andExpect(jsonPath("$[1].name").value("Movie two"))
                .andExpect(jsonPath("$[2].name").value("Movie three"));
    }


}
