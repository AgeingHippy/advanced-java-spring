/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.PATCH;

import java.util.Map;

import com.codingnomads.springweb.resttemplate.PATCH.models.User;
import com.codingnomads.springweb.resttemplate.PATCH.models.UserResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PatchMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PatchMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            final String userUrl = "http://demo.codingnomads.co:8080/tasks_api/users/{userId}";
            long[] userIds = new long[2];
            User user;
            UserResponseObject userResponseObject;
            HttpEntity<User> userHttpEntity;
            ResponseEntity<UserResponseObject> userResponseEntity;

            userIds[0] = createUser();
            userIds[1] = createUser();

            userResponseObject = restTemplate.getForObject(userUrl, UserResponseObject.class, Map.of("userId", userIds[0]));
            System.out.println(userResponseObject);

            System.out.println("== patchForObject ==");
            user = User.builder().id(userIds[0]).firstName("name patchForObject").build();
            userResponseObject = restTemplate.patchForObject(userUrl, user, UserResponseObject.class, Map.of("userId", userIds[0]));
            System.out.println(userResponseObject);

            System.out.println("== patch using exchange ==");
            userResponseObject = restTemplate.getForObject(userUrl, UserResponseObject.class, Map.of("userId", userIds[1]));
            System.out.println(userResponseObject);

            user = User.builder().id(userIds[1]).lastName("name patch with exchange").build();
            userHttpEntity = new HttpEntity<>(user);
            userResponseEntity = restTemplate.exchange(
                    userUrl,
                    HttpMethod.PATCH,
                    userHttpEntity,
                    UserResponseObject.class,
                    Map.of("userId", userIds[1]));

            System.out.println(userResponseEntity);


//            // create an empty Task
//            Task task = new Task();
//
//            // be sure to use a valid task id
//            task.setId(169);
//
//            // set fields you want to change. All other fields are null and will not be updated
//            task.setName("use patchForObject()");
//            task.setDescription("this task was updated using patchForObject()");
//
//            // send the PATCH request using the URL, the Task created above and the ResponseObject Class
//            ResponseObject objectResponse = restTemplate.patchForObject(
//                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + task.getId(), task, ResponseObject.class);
//
//            System.out.println(Objects.requireNonNull(objectResponse));
//
//            task.setName("PATCH using exchange()");
//            task.setDescription("This task was updated using PATCH");
//
//            HttpEntity<Task> httpEntity = new HttpEntity<>(task);
//            ResponseEntity<ResponseObject> response = restTemplate.exchange(
//                    "http://demo.codingnomads.co:8080/tasks_api/tasks/" + task.getId(),
//                    HttpMethod.PATCH,
//                    httpEntity,
//                    ResponseObject.class);
//
//            System.out.println(Objects.requireNonNull(response));
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

}
