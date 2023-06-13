package com.gouda.notquizlet.controller;

import com.gouda.notquizlet.entity.User;
import com.gouda.notquizlet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String userPanel(@PathVariable String username, Model model){
        User user = userService.findByUsername(username);

        if (user != null) {
            model.addAttribute("user", user);
        }
        else {
            return "error/404";
        }

        return "user";
    }
}
