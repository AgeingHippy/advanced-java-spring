/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.custompermissions;

import com.codingnomads.springsecurity.authorization.custompermissions.models.MyGrantedAuthority;
import com.codingnomads.springsecurity.authorization.custompermissions.models.User;
import com.codingnomads.springsecurity.authorization.custompermissions.repositories.AuthRepository;
import com.codingnomads.springsecurity.authorization.custompermissions.repositories.UserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PermissionsDemo implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PermissionsDemo.class);
    }

    @Override
    public void run(String... args) throws Exception {

        User user;
        User user1;
        User user2;
        User user3;
        User user4;

        if (userRepository.findAll().isEmpty()) {
            user = new User("x", passwordEncoder.encode("x"));
            user1 = new User("user1@email.com", passwordEncoder.encode("12345678"));
            user2 = new User("user2@email.com", passwordEncoder.encode("12345678"));
            user3 = new User("user3@email.com", passwordEncoder.encode("12345678"));
            user4 = new User("user4@email.com", passwordEncoder.encode("12345678"));
            userRepository.saveAll(Arrays.asList(user,user1, user2, user3, user4));
        } else {
            user = userRepository.findByEmail("x");
            user1 = userRepository.findByEmail("user1@email.com");
            user2 = userRepository.findByEmail("user2@email.com");
            user3 = userRepository.findByEmail("user3@email.com");
            user4 = userRepository.findByEmail("user4@email.com");
        }

        if (authRepository.findAll().isEmpty()) {
            authRepository.save(new MyGrantedAuthority(user.getClass().getTypeName(), user.getId(), "READ"));
            authRepository.save(new MyGrantedAuthority(user.getClass().getTypeName(), user.getId(), "DELETE"));

            authRepository.save(new MyGrantedAuthority(user1.getClass().getTypeName(), user1.getId(), "READ"));
            authRepository.save(new MyGrantedAuthority(user1.getClass().getTypeName(), user1.getId(), "DELETE"));

            authRepository.save(new MyGrantedAuthority(user2.getClass().getTypeName(), user2.getId(), "READ"));
            authRepository.save(new MyGrantedAuthority(user2.getClass().getTypeName(), user2.getId(), "DELETE"));

            authRepository.save(new MyGrantedAuthority(user3.getClass().getTypeName(), user3.getId(), "READ"));
            authRepository.save(new MyGrantedAuthority(user3.getClass().getTypeName(), user4.getId(), "UPDATE"));
            authRepository.save(new MyGrantedAuthority(user3.getClass().getTypeName(), user3.getId(), "DELETE"));

            authRepository.save(new MyGrantedAuthority(user4.getClass().getTypeName(), user4.getId(), "READ"));
            authRepository.save(new MyGrantedAuthority(user4.getClass().getTypeName(), user4.getId(), "UPDATE"));
            authRepository.save(new MyGrantedAuthority(user4.getClass().getTypeName(), user4.getId(), "READALL"));
            authRepository.save(new MyGrantedAuthority(user4.getClass().getTypeName(), user4.getId(), "DELETE"));
            authRepository.save(new MyGrantedAuthority(user4.getClass().getTypeName(), user4.getId(), "DELETEALL"));
        }
    }
}
