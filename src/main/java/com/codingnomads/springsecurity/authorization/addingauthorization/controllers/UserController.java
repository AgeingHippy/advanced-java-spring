/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.addingauthorization.controllers;

import com.codingnomads.springsecurity.authorization.addingauthorization.models.UserMeta;
import com.codingnomads.springsecurity.authorization.addingauthorization.models.UserPrincipal;
import com.codingnomads.springsecurity.authorization.addingauthorization.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    CustomUserService userDetailsService;

    @PostMapping("/update-user")
    @PreAuthorize("#userToUpdate.id == authentication.principal.userMeta.id") //only authorised to update own metadata
    public UserMeta updateUser(@RequestBody UserMeta userToUpdate) {
        return userDetailsService.updateUserMeta(userToUpdate);
    }

    @GetMapping("/current-user")
    public UserMeta getUser(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return principal.getUserMeta();
    }

    @GetMapping("/users")
    @PostFilter("filterObject.id != authentication.principal.userMeta.id") //only return other user's Meta
    List<UserMeta> getUsers() {
        List<UserMeta> users = userDetailsService.getAllUserMeta();
        return users;
    }

    /*
       Method Security Annotations

       @RolesAllowed("ROLE_USER")
       @PreAuthorize("#id != 1")
       @PostAuthorize("returnObject.ownerUsername == authentication.principal.username")
       @PreFilter(value = "filterObject != shutdown", filterTarget = "commands")
       @PostFilter("filterObject.id <= 20")
    */
}
