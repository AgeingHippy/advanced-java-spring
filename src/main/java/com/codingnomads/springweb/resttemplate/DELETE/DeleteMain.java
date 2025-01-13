/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.DELETE;

import com.codingnomads.springweb.resttemplate.DELETE.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.DELETE.models.Task;
import com.codingnomads.springweb.resttemplate.DELETE.models.User;
import com.codingnomads.springweb.resttemplate.DELETE.models.UserResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class DeleteMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DeleteMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

//            nomadMessingWithTask(createUser());

            messWithUsers();

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

    private void messWithUsers() {
        final String userUrl = "http://demo.codingnomads.co:8080/tasks_api/users/{userId}";
        long[] userIds = new long[3];
        User user;
        UserResponseObject userResponseObject;
        HttpEntity<User> userHttpEntity;
        ResponseEntity<UserResponseObject> userResponseEntity;

        userIds[0] = createUser();
        userIds[1] = createUser();
        userIds[2] = createUser();

        System.out.println("== delete user using .delete()");
        userResponseObject = restTemplate.getForObject(userUrl, UserResponseObject.class, Map.of("userId", userIds[0]));
        System.out.println(userResponseObject);
        restTemplate.delete(userUrl, Map.of("userId", userIds[0]));
        try {
            userResponseObject = restTemplate.getForObject(userUrl, UserResponseObject.class, Map.of("userId", userIds[0]));
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("== delete user using .exchange()");
        userResponseObject = restTemplate.getForObject(userUrl, UserResponseObject.class, Map.of("userId", userIds[1]));
        System.out.println(userResponseObject);

        userHttpEntity = new HttpEntity<>(Objects.requireNonNull(userResponseObject).getData());
        try {
            restTemplate.exchange(userUrl,
                    HttpMethod.DELETE,
                    userHttpEntity,
                    UserResponseObject.class,
                    Map.of("userId", userIds[1]));

            userResponseObject = restTemplate.getForObject(userUrl, UserResponseObject.class, Map.of("userId", userIds[1]));
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("== delete user using .exchange() WITH NULL httpEntity and investigate responseEntity");
        userResponseObject = restTemplate.getForObject(userUrl, UserResponseObject.class, Map.of("userId", userIds[2]));
        System.out.println(userResponseObject);


        ResponseEntity<String> responseEntity = restTemplate.exchange(userUrl,
                HttpMethod.DELETE,
                null,
                String.class,
                Map.of("userId", userIds[2]));
        System.out.println(responseEntity);
        try {
            userResponseObject = restTemplate.getForObject(userUrl, UserResponseObject.class, Map.of("userId", userIds[2]));
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
    }

    private void nomadMessingWithTask(long userId) throws Exception {
        Task newTask = Task.builder()
                .name("should be deleted")
                .description("used in a delete RestTemplate example. If you see this something went wrong. Oops")
                // be sure to enter a valid user id
                .userId(userId)
                .completed(false)
                .build();

        // POST new task to server
        ResponseObject responseObject = restTemplate.postForObject(
                "http://demo.codingnomads.co:8080/tasks_api/tasks/", newTask, ResponseObject.class);

        // confirm data was returned & avoid NullPointerExceptions
        if (responseObject == null) {
            throw new Exception("The server did not return anything. Not even a ResponseObject!");
        } else if (responseObject.getData() == null) {
            throw new Exception("The server encountered this error while creating the task:"
                    + responseObject.getError().getMessage());
        } else {
            newTask = responseObject.getData();
        }

        System.out.println("The task was successfully created");
        System.out.println(newTask);

        // delete the newTask using the ID the server returned
        restTemplate.delete("http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId());
        System.out.println("The task was also successfully deleted");

        // try to GET, verify record was deleted
        try {
            restTemplate.getForEntity(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId(), ResponseObject.class);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }

        newTask = Task.builder()
                .name("newtask for exchange delete be deleted")
                .description("used in a delete RestTemplate exchange example.")
                // be sure to enter a valid user id
                .userId(userId)
                .completed(false)
                .build();

        // POST new task to server
        responseObject = restTemplate.postForObject(
                "http://demo.codingnomads.co:8080/tasks_api/tasks/", newTask, ResponseObject.class);
        System.out.println(responseObject);
        newTask = responseObject.getData();

        // delete using exchange()
        HttpEntity<Task> httpEntity = new HttpEntity<>(newTask);
        try {
            restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId(),
                    HttpMethod.DELETE,
                    httpEntity,
                    ResponseObject.class);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
