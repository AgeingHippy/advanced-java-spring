/* CodingNomads (C)2024 */
package com.codingnomads.springtest.lab;

import com.codingnomads.springtest.lab.entity.Movie;
import com.codingnomads.springtest.lab.repository.MovieRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = SpringTestLab.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MovieRepository movieRepository;

    @Test
    @Order(1)
    public void testGetAllMoviesSuccess() throws Exception {
        mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[1].name").value("The Pursuit of Happyness"));
    }

    @Test
    @Order(2)
    public void testGetAllMoviesSuccess2() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<Movie> movies = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Movie>>(){});

        assertThat(movies.get(0).getName()).isEqualTo("The Shawshank Redemption");
        assertThat(movies.get(1).getName()).isEqualTo("The Pursuit of Happyness");
    }

    @Test
    @Order(3)
    public void testGetMoviesWithMinimumRatingSuccess() throws Exception {

        mockMvc.perform(get("/minimum/9.0"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("The Shawshank Redemption"));

        mockMvc.perform(get("/minimum/8.0"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[1].name").value("The Pursuit of Happyness"));
    }

    @Test
    @Order(4)
    public void testGetMoviesWithMinimumRatingFailure() throws Exception {

        mockMvc.perform(get("/minimum/9.5"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Order(5)
    public void testGetAllMoviesFailure() throws Exception {
        movieRepository.deleteAll();
        mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(jsonPath("$").isEmpty());
    }


}
