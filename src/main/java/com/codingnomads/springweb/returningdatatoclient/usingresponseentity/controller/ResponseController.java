/* CodingNomads (C)2024 */
package com.codingnomads.springweb.returningdatatoclient.usingresponseentity.controller;

import com.codingnomads.springweb.returningdatatoclient.usingresponseentity.model.User;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    User user = new User(1, "Test User", "test@email.com");

    @GetMapping("/constructor")
    public ResponseEntity<User> constructorMethod() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("TEST", "TEST HEADER");
        headers.add("Location", "/users/" + user.getId());
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @GetMapping("/builder")
    public ResponseEntity<User> builderMethod() {
        return ResponseEntity.created(URI.create("/users/" + user.getId()))
                .header("TEST", "TEST HEADER")
                .body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        if (user.getId() == id) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/practice")
    public ResponseEntity<?> doPractice() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/practice/1")
    public ResponseEntity<?> doPractice1() {
        Map<String,String> myMap = new HashMap<>();
        myMap.put("A","a");
        myMap.put("C","c");
        return ResponseEntity.ok().body(myMap);
    }

    @GetMapping("/practice/2")
    public ResponseEntity<?> doPractice2() {
        return ResponseEntity.status(200)
                .header("H1","hache one")
                .header("foo","bar")
                .body("a random string");
    }

    @GetMapping("/practice/3")
    public ResponseEntity<?> doPractice3() {
        return ResponseEntity.status(201)
                .header("H1","hache one","anotherH")
                .header("foo","bar")
                .body("a random string and some headers");
    }

}
