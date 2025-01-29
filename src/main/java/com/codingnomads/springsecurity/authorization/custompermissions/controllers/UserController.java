/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.custompermissions.controllers;

import com.codingnomads.springsecurity.authorization.custompermissions.models.User;
import com.codingnomads.springsecurity.authorization.custompermissions.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home() {
        return "permissions";
    }

    @GetMapping("/user")
    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public User getEntityById(@RequestParam String email) {
        return userService.getUser(email);
    }

    @GetMapping("/user/delete/{id}")
    @ResponseBody
    @PreAuthorize(
            "hasPermission(#id, 'com.codingnomads.springsecurity.authorization.custompermissions.models.User', 'DELETE') or " +
                    "hasPermission(-1L, 'com.codingnomads.springsecurity.authorization.custompermissions.models.User', 'DELETEALL')")
    public String deleteEntity(@PathVariable Long id) {
        userService.deleteUser(id);
        return ("deleted user with id: " + id);
    }

    @GetMapping("/user/delete-all")
    @ResponseBody
    @PreAuthorize("hasPermission(-1L, 'com.codingnomads.springsecurity.authorization.custompermissions.models.User', 'DELETEALL')")
    public String deleteEntity() {
        userService.deleteAllUsers();
        return ("deleted all users");
    }

    @GetMapping("/user/all")
    @ResponseBody
    @PostFilter("hasPermission(filterObject.id, 'com.codingnomads.springsecurity.authorization.custompermissions.models.User', 'READ') " +
            " or hasPermission(-1L, 'com.codingnomads.springsecurity.authorization.custompermissions.models.User', 'READALL')")
    public List<User> getAllUsers(@CurrentSecurityContext SecurityContext context) {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @PostMapping("/num")
    @ResponseBody
    @PreFilter("filterObject %2 == 1")
    public List<Integer> getNum(@RequestParam List<Integer> numbers) {
        return numbers;
    }

}
