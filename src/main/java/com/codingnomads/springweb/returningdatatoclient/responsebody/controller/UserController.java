/* CodingNomads (C)2024 */
package com.codingnomads.springweb.returningdatatoclient.responsebody.controller;

import com.codingnomads.springweb.returningdatatoclient.responsebody.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    public User user = User.builder()
            .id(1000)
            .name("Spring Dev")
            .email("dev@codingnomads.com")
            .build();

    public User user1 = User.builder()
            .id(1000)
            .name("Spring Dev1")
            .email("dev1@codingnomads.com")
            .build();

    public User user2 = User.builder()
            .id(1000)
            .name("Spring Dev2")
            .email("dev2@codingnomads.com")
            .build();

    public User user3 = User.builder()
            .id(1000)
            .name("Spring Dev3")
            .email("dev3@codingnomads.com")
            .build();

        // using ResponseBody to return a POJO
    @ResponseBody
    @GetMapping("/response-body")
    public User userResponseBody() {
        return user;
    }

    // using ResponseEntity to return POJO
    @GetMapping("/response-entity")
    public ResponseEntity<User> userResponseEntity() {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // returning a POJO without ResponseBody or using a ResponseEntity - error expected
    @GetMapping("/user")
    public User user() {
        return user;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> users() {
        return Arrays.asList(user1, user2, user3);
    }

}
