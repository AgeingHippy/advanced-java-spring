/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.PUT;

import com.codingnomads.springweb.resttemplate.PUT.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.PUT.models.Task;
import com.codingnomads.springweb.resttemplate.PUT.models.User;
import com.codingnomads.springweb.resttemplate.PUT.models.UserResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Random;

@SpringBootApplication
public class PutMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PutMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            long userId = createUser();

            // use a valid task id
            long taskId = createTask(userId);

            try {
                messWithTask(taskId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            messWithUser(userId);


        };
    }

    private long createUser() {
        int num = (int) (Math.random() * 1001);
        User user = User.builder()
                .email("em" + num + "@host" + num + ".com")
                .firstName("first" + num)
                .lastName("last" + num).build();
        UserResponseObject userResponseObject = restTemplate.postForObject(
                "http://demo.codingnomads.co:8080/tasks_api/users", user, UserResponseObject.class);
        if (userResponseObject != null && userResponseObject.getData() != null) {
            user = userResponseObject.getData();
        } else {
            throw new RuntimeException("User not created or not found");
        }
        return user.getId();
    }

    private long createTask(long userId) {
        Task task = Task.builder()
                .userId(userId)
                .name("A new task for user " + userId)
                .description("A random description of a random task " + userId)
                .completed(false)
                .build();

        ResponseObject responseObject = restTemplate.postForObject(
                "http://demo.codingnomads.co:8080/tasks_api/tasks",
                task,
                ResponseObject.class);
        if (responseObject != null && responseObject.getData() != null) {
            task = responseObject.getData();
        } else {
            throw new RuntimeException("User not created or not found");
        }
        return task.getId();
    }

    private void messWithUser(long userId) {
        final String usersUrl = "http://demo.codingnomads.co:8080/tasks_api/users/{userId}";
        UserResponseObject userResponseObject =
                restTemplate.getForObject(usersUrl, UserResponseObject.class, Map.of("userId", userId));
        User user = Objects.requireNonNull(userResponseObject).getData();

        System.out.println(userResponseObject);

        System.out.println("== modify user with .put() ==");
        user.setFirstName("first .put");
        user.setLastName("last .put");
        restTemplate.put(usersUrl, user, Map.of("userId", userId));
        userResponseObject = restTemplate.getForObject(usersUrl, UserResponseObject.class, Map.of("userId", userId));
        System.out.println(userResponseObject);

        System.out.println("== Modified using .exchange ==");
        user = userResponseObject.getData();
        user.setFirstName(".exchange first");
        user.setLastName(".exchange last");

        HttpEntity<User> httpEntity = new HttpEntity<>(user);

        ResponseEntity<UserResponseObject> responseEntity = restTemplate.exchange(
                usersUrl, HttpMethod.PUT, httpEntity, UserResponseObject.class, user.getId()
        );

        userResponseObject = responseEntity.getBody();
        System.out.println(responseEntity);
    }

    private void messWithTask(long taskId) throws Exception {
        // request Task 5 from server
        ResponseObject responseObject = restTemplate.getForObject(
                "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskId, ResponseObject.class);

        // confirm data was retrieved & avoid NullPointerExceptions
        Task taskToUpdate;
        if (responseObject == null) {
            throw new Exception("The server did not return anything. Not even a ResponseObject!");
        } else if (responseObject.getData() == null) {
            throw new Exception("The task with ID " + taskId + " could not be found");
        } else {
            taskToUpdate = responseObject.getData();
        }

        // update the task information
        System.out.println(responseObject);
        taskToUpdate.setName("updated using put() - video demo ");
        taskToUpdate.setDescription("this task was updated using RestTemplate's put() method - video demo");
        taskToUpdate.setCompleted(true);

        // use put to update the resource on the server
        restTemplate.put("http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskToUpdate.getId(), taskToUpdate);
        // get the task to verify update
        responseObject = restTemplate.getForObject(
                "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskId, ResponseObject.class);
        System.out.println(responseObject.toString());

        taskToUpdate.setName("updated using exchange() PUT - video demo 2");
        taskToUpdate.setDescription("this task was updated using RestTemplate's exchange() method - video demo 2");

        // create an HttpEntity wrapping the task to update
        HttpEntity<Task> httpEntity = new HttpEntity<>(taskToUpdate);
        // use exchange() to PUT the HttpEntity, and map the response to a ResponseEntity
        ResponseEntity<ResponseObject> response = restTemplate.exchange(
                "http://demo.codingnomads.co:8080/tasks_api/tasks/" + taskToUpdate.getId(),
                HttpMethod.PUT,
                httpEntity,
                ResponseObject.class);
        System.out.println(response.toString());
    }
}
