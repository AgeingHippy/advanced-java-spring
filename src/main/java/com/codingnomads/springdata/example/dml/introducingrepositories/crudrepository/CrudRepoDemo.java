/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.introducingrepositories.crudrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class CrudRepoDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudRepoDemo.class);
    }

    // autowire the UserRepo into the class to gain access to the CRUD methods
    @Autowired
    UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        // create new user
        User user =
                User.builder().firstName("Bobby").lastName("Bobbert").age(56).build();
        User user2 =
                User.builder().firstName("Joanne").lastName("Joanna").age(36).build();

        // save user and assign what is returned to the user variable.
        user = userRepo.save(user);
        user2 = userRepo.save(user2);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(User.builder().firstName("Jack").lastName("Sparrow").age(44).build());
        userList.add(User.builder().firstName("John").lastName("Jones").age(54).build());
        userList.add(User.builder().firstName("Robin").lastName("Williams").age(64).build());
        userList.add(User.builder().firstName("Lucy").lastName("Letby").age(74).build());
        userList.add(User.builder().firstName("William").lastName("Shakespeare").age(84).build());

        userRepo.saveAll(userList);

        Iterable<User> users = userRepo.findAll();

        for (User u : users) {
            System.out.println(u.toString());
        }

        // delete the user using the id of the inserted user object
//        userRepo.deleteById(user.getId());
//        userRepo.deleteById(user2.getId());

        userRepo.deleteAll();
    }
}
