/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.addingauthorization.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute
    public void addUserInfo(HttpServletRequest request, Model model) {
        model.addAttribute("remoteUser", request.getRemoteUser());
    }

    @GetMapping("/")
    public String homePage() {
        return "authorization/home";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "authorization/admin";
    }

    @GetMapping("/superu")
    public String superUPage() {
        return "authorization/superu";
    }

    @GetMapping("/mas")
    @PreAuthorize("#id != 1")
    public String testMas(int id) {
        return "authorization/home";
    }

    @GetMapping("/random/su")
    public String randomSu(Model model) {
        model.addAttribute("userPath","/random/su");
        return "authorization/random";
    }

    @GetMapping("/random/admin")
    public String randomAdmin(Model model) {
        model.addAttribute("userPath","/random/admin");
        return "authorization/random";
    }

    @GetMapping("/random/user")
    public String randomUser(Model model) {
        model.addAttribute("userPath","/random/user");
        return "authorization/random";
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
