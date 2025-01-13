/* CodingNomads (C)2024 */
package com.codingnomads.springweb.springrestcontrollers.simpledemo.controller;

import com.codingnomads.springweb.springrestcontrollers.simpledemo.model.ResponseObject;
import com.codingnomads.springweb.springrestcontrollers.simpledemo.model.ResponseObjectList;
import com.codingnomads.springweb.springrestcontrollers.simpledemo.model.Task;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api")
public class HelloWorldController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello Spring Developer!";
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting(@PathVariable(name = "name") String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping(path = "/goodbye", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String adieu1(@RequestParam(required = false) String name) {
        return "Adieu, bon voyage" + (!StringUtils.isBlank(name) ? " fair "+ name : "") ;
    }

    @RequestMapping(path = "/goodbye/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String adieu2(@PathVariable(name="name") String name) {
        return "Cheers "+name;
    }

    @RequestMapping(path = "/tasks", method = RequestMethod.GET)
    public List<Task> getTasks() {
        List<Task> tasks;
        ResponseObjectList responseObject;
        responseObject = restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/tasks", ResponseObjectList.class);
        tasks = Objects.requireNonNull(responseObject).getData();
        return tasks;
    }

    @RequestMapping(path = "/tasks/{id}", method = RequestMethod.GET)
    public Task getTask(@PathVariable(name="id") String id) {
        Task task;
        ResponseObject responseObject;
        responseObject = restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/tasks/{id}", ResponseObject.class, Map.of("id",id));
        task = Objects.requireNonNull(responseObject).getData();
        return task;
    }

}
