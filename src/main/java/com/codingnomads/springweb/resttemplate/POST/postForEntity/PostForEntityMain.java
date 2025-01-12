/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.POST.postForEntity;

import com.codingnomads.springweb.resttemplate.POST.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.POST.models.Task;
import java.util.Objects;

import com.codingnomads.springweb.resttemplate.POST.models.User;
import com.codingnomads.springweb.resttemplate.POST.models.UserResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PostForEntityMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForEntityMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Task newTask = Task.builder()
                    .name("learn how to use postForEntity()")
                    .description("get comfortable using the RestTemplate postForEntity() method")
                    // be sure to use valid user id
                    .userId(380)
                    .completed(false)
                    .build();

            ResponseEntity<ResponseObject> responseEntity = null;
            try {
                responseEntity = restTemplate.postForEntity(
                        "http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);

                if (responseEntity != null && responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                    System.out.println(Objects.requireNonNull(responseEntity.getBody()));
                } else {
                    System.out.println(
                            Objects.requireNonNull(responseEntity.getBody()).getError());
                }
            }
            catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }




            User user = User.builder()
                    .email("em1@mail.com")
                    .firstName("Jack")
                    .lastName("Sparrow").build();

            ResponseEntity<UserResponseObject> userResponseEntity = restTemplate.postForEntity(
                    "http://demo.codingnomads.co:8080/tasks_api/users",
                    user,
                    UserResponseObject.class);
            if (userResponseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                System.out.println(Objects.requireNonNull(userResponseEntity.getBody()));
            } else {
                System.out.println(
                        Objects.requireNonNull(userResponseEntity.getBody()).getError());
            }

            user = User.builder()
                    .email("em2@mail.com")
                    .firstName("Bob")
                    .lastName("Walker").build();

            userResponseEntity = restTemplate.postForEntity(
                    "http://demo.codingnomads.co:8080/tasks_api/users",
                    user,
                    UserResponseObject.class);
            if (userResponseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                System.out.println(Objects.requireNonNull(userResponseEntity.getBody()));
            } else {
                System.out.println(
                        Objects.requireNonNull(userResponseEntity.getBody()).getError());
            }

        };
    }
}

