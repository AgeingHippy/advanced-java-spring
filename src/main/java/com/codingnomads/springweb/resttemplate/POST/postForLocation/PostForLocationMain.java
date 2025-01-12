/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.POST.postForLocation;

import com.codingnomads.springweb.resttemplate.POST.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.POST.models.Task;

import java.net.URI;
import java.util.Objects;

import com.codingnomads.springweb.resttemplate.POST.models.User;
import com.codingnomads.springweb.resttemplate.POST.models.UserResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PostForLocationMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForLocationMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            User user;
//            user = User.builder()
//                    .email("em31@em.com")
//                    .firstName("Bob1b")
//                    .lastName("Builder1b").build();
//
//            ResponseEntity<UserResponseObject> userResponseEntity = restTemplate.postForEntity(
//                    "http://demo.codingnomads.co:8080/tasks_api/users",user, UserResponseObject.class);
//
//            Task newTask = Task.builder()
//                    .name("learn how to use postForLocation()")
//                    .description("get comfortable using the RestTemplate postForLocation() method")
//                    // be sure to use a valid user id
//                    .userId(userResponseEntity.getBody().getData().getId())
//                    .completed(false)
//                    .build();
//
//            // use postForLocation() to get the URL for the new resource
//            URI returnedLocation = restTemplate.postForLocation(
//                    "http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);
//
//            System.out.println(Objects.requireNonNull(returnedLocation));
//
//            ResponseEntity<?> responseEntity = restTemplate.postForEntity(
//                    "http://demo.codingnomads.co:8080/tasks_api/tasks", newTask, ResponseObject.class);
//
//            System.out.println(responseEntity.getHeaders().get("Location"));

            user = User.builder()
                    .email("em36@em.com")
                    .firstName("Bob6")
                    .lastName("Builder6").build();

            URI userUri = restTemplate.postForLocation("http://demo.codingnomads.co:8080/tasks_api/users", user);

            ResponseEntity<UserResponseObject> userResponseEntity2 = restTemplate.getForEntity(
                    "http://demo.codingnomads.co:8080/tasks_api" + userUri, UserResponseObject.class);

            System.out.println(userResponseEntity2.getBody().toString());
        };
    }
}
