/* CodingNomads (C)2024 */
package com.codingnomads.springweb.gettingdatafromclient.requestbody.controllers;

import com.codingnomads.springweb.gettingdatafromclient.requestbody.models.Task;
import com.codingnomads.springweb.gettingdatafromclient.requestbody.repositories.TaskRepository;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody(required = true) Task task) throws URISyntaxException {
        if (StringUtils.isEmpty(task.getName()) || task.getCompleted() == null) {
            task.setCreatedAt(null);
            return ResponseEntity.badRequest().body(task);
        }
        final Task savedTask = taskRepository.save(Task.builder()
                .completed(task.getCompleted())
                .name(task.getName())
                .build());

        return ResponseEntity.created(new URI("/api/tasks/" + savedTask.getId()))
                .body(savedTask);
    }

    @PutMapping("/api/tasks")
    public ResponseEntity<Task> putTask(@RequestBody Task taskPut) {
        if (taskPut.getId() == null || taskPut.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }

        if (!taskRepository.existsById(taskPut.getId())) {
            return ResponseEntity.badRequest().build();
        }
        taskRepository.save(taskPut);

        Task task = taskRepository.findById(taskPut.getId()).get();
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/api/tasks/{id}")
    public ResponseEntity<?> putTask2(@RequestBody Task taskPatch, @PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Task not found");
        }

        Task task = taskRepository.findById(id).get();

        task.setName(taskPatch.getName() != null ? taskPatch.getName() : task.getName());
        task.setCompleted(taskPatch.getCompleted() != null ? taskPatch.getCompleted() : task.getCompleted());

        task = taskRepository.save(task);
        return ResponseEntity.ok(task);
    }

    @PostMapping(value = "/print")
    public ResponseEntity<?> printMessage(@RequestBody(required = false) String message) {

        if (message == null) {
            message = "You did not pass in a message.";
        }
        System.out.println(message);

        if (message.equalsIgnoreCase("I'm a teapot")) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(message);
        } else {
            return ResponseEntity.ok().body(message);
        }
    }


}
