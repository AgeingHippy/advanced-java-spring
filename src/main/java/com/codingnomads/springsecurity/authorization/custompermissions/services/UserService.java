/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.custompermissions.services;

import com.codingnomads.springsecurity.authorization.custompermissions.models.User;
import com.codingnomads.springsecurity.authorization.custompermissions.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
