package com.gouda.notquizlet.controller;

import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/index", "/home"})
    public String home(Principal principal, Model model) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
            return "user/home";
        }
        return "main";
    }
}
